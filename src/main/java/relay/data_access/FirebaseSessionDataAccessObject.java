package relay.data_access;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

import com.google.api.core.ApiFuture;
import com.google.api.gax.paging.Page;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;

import relay.entity.Session;
import relay.entity.SessionFactory;
import relay.exceptions.ResourceNotFoundException;

public class FirebaseSessionDataAccessObject {

	private final Firestore db;
	private final Bucket bucket;

	public FirebaseSessionDataAccessObject() {
		this.db = FirestoreSingleton.get();
		this.bucket = StorageSingleton.get();
	}

	/**
	 * @param session
	 */
	public void save(Session session) {
		try {
			Map<String, Object> sessionMap = session.convertToMap();
			String sessionID = session.getSessionID();
			if (sessionMap == null)
				throw new NullPointerException();

			if (exists(sessionID) && sessionID != null) {
				db.collection("sessions").document(sessionID).set(sessionMap);
			} else {
				ApiFuture<DocumentReference> sessionDocument = db.collection("sessions").add(sessionMap);
				String sessionDocumentID = sessionDocument.get().getId();
				session.setSessionID(sessionDocumentID);

				String alphaNumericCode = sessionDocumentID.toUpperCase();

				db.collection("sessions").document(sessionDocumentID).update(
						"sessionCode",
						alphaNumericCode).get();
				session.setAlphaNumericCode(alphaNumericCode);
				System.out.println(session.getAlphaNumericCode());
			}

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public Session read(String sessionID) {
		if (sessionID == null)
			throw new NullPointerException();
		if (!exists(sessionID))
			throw new ResourceNotFoundException("No such document exists.");

		try {
			ApiFuture<DocumentSnapshot> retrievedSessionDocument = db.collection("sessions")
					.document(sessionID).get();
			DocumentSnapshot sessionDocumentSnapshot = retrievedSessionDocument.get();
			Map<String, Object> sessionDocumentData = sessionDocumentSnapshot.getData();

			if (sessionDocumentData == null)
				throw new NullPointerException();

			return SessionFactory.createSessionFromMap(sessionDocumentData);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean exists(String sessionID) {
		if (sessionID == null)
			return false;

		try {
			ApiFuture<DocumentSnapshot> retrievedSessionDocument = db.collection("sessions")
					.document(sessionID).get();
			DocumentSnapshot sessionDocumentSnapshot = retrievedSessionDocument.get();
			return sessionDocumentSnapshot.exists();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void delete(String sessionID) {
		if (sessionID == null)
			throw new NullPointerException();
		if (!exists(sessionID))
			throw new ResourceNotFoundException("No such document exists.");
		try {
			ApiFuture<WriteResult> deleteResult = db.collection("sessions").document(sessionID).delete();
			deleteResult.get();

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public void uploadImageToFirebaseStorage(Session session) {
		try {
			String sessionID = session.getSessionID();
			Image qrCodeImage = session.getQrCodeImage();
			String imageFileName = sessionID + ".png";
			if (sessionID == null)
				throw new NullPointerException();

			File outputFile = new File(imageFileName);
			ImageIO.write((RenderedImage) qrCodeImage, "png", outputFile);
			bucket.create(imageFileName, Files.readAllBytes(outputFile.toPath()));
			outputFile.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean fileExists(String fileName) {
		Page<Blob> blobs = bucket.list();

		for (Blob blob : blobs.iterateAll()) {
			if (blob.getName().equals(fileName))
				return true;
		}
		return false;
	}

	public void deleteFile(String fileName) {
		if (!fileExists(fileName))
			throw new ResourceNotFoundException("No such file exists.");
		else
			bucket.get(fileName).delete();
	}
}
