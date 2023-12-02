package relay.data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import relay.exceptions.ResourceNotFoundException;
import relay.entity.Instructor;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Provides data access methods for interacting with the Firebase Firestore
 * database
 * to perform CRUD operations on Instructor entities.
 */
@Validated
@Component
public class FirebaseInstructorDataAccessObject {

	Firestore db;

	public FirebaseInstructorDataAccessObject() {
		this.db = FirestoreSingleton.get();
	}

	/**
	 * Saves the provided Instructor object to the Firestore database.
	 *
	 * @param instructor The Instructor object to be saved.
	 */
	public void save(Instructor instructor) {
		if (exists(instructor.getInstructorID()))
			update(instructor);
		else
			create(instructor);
	}

	private void create(Instructor instructor) {
		ApiFuture<DocumentReference> docRef = db.collection("instructors").add(instructor);
		try {
			instructor.setInstructorID(docRef.get().getId());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	private void update(Instructor instructor) {
		String instructorID = instructor.getInstructorID();
		if (instructorID == null)
			throw new NullPointerException();
		db.collection("instructors").document().set(instructor);
	}

	/**
	 * Reads an Instructor object from the Firestore database based on the provided
	 * instructorID.
	 *
	 * @param instructorID The unique identifier of the Instructor.
	 * @return The retrieved Instructor object, or null if not found or an error
	 *         occurs.
	 * @throws ResourceNotFoundException if the instructor does not exist in
	 *                                   Firestore
	 * @throws NullPointerException      if the converted instructor document
	 *                                   variable is null
	 */
	public Instructor read(String instructorID) {
		if (instructorID == null)
			throw new NullPointerException();
		if (!exists(instructorID))
			throw new ResourceNotFoundException("No such document exists.");
		try {
			ApiFuture<DocumentSnapshot> future = db.collection("instructors").document(instructorID)
					.get();
			DocumentSnapshot instructorDocument = future.get();
			Map<String, Object> instructorDocumentData = instructorDocument.getData();

			if (instructorDocumentData == null)
				throw new NullPointerException();

			String firstName = (String) instructorDocumentData.get("firstName");
			String lastName = (String) instructorDocumentData.get("lastName");
			String emailAddress = (String) instructorDocumentData.get("emailAddress");
			Instructor instructor = new Instructor(firstName, lastName, emailAddress);

			instructor.setInstructorID(instructorID);
			return instructor;

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Checks if an Instructor object with the provided instructorID exists in the
	 * Firestore database.
	 *
	 * @param instructorID The unique identifier of the Instructor.
	 * @return True if the Instructor exists, false otherwise.
	 */
	public boolean exists(String instructorID) {
		if (instructorID == null || instructorID.isEmpty())
			return false;

		try {
			ApiFuture<DocumentSnapshot> future = db.collection("instructors")
					.document(instructorID)
					.get();
			DocumentSnapshot instructorDocument = future.get();
			return instructorDocument.exists();

		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes an Instructor object from the Firestore database based on the
	 * provided instructorID.
	 *
	 * @param instructorID The unique identifier of the Instructor to be deleted.
	 * @throws ResourceNotFoundException if the instructor does not exist in
	 *                                   Firestore
	 * @throws NullPointerException      if the instructorID parameter is null
	 */
	public void delete(String instructorID) throws ResourceNotFoundException {
		if (!exists(instructorID))
			throw new ResourceNotFoundException("No such document exists.");

		if (instructorID == null)
			throw new NullPointerException();

		try {
			ApiFuture<WriteResult> deleteResult = db.collection("instructors").document(instructorID)
					.delete();
			deleteResult.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
