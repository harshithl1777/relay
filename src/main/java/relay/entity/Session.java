package relay.entity;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import relay.data_access.StorageSingleton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Session {
	private ArrayList<AttendanceRecord> attendance;
	private String classID;
	private Instructor instructor;
	private LocalDateTime startedAt;
	private String alphaNumericCode;
	private Image qrCodeImage;

	public Session(ArrayList<AttendanceRecord> attendance, String classID, Instructor instructor,
				   LocalDateTime startedAt, String alphaNumericCode) {
		this.attendance = attendance;
		this.classID = classID;
		this.instructor = instructor;
		this.startedAt = startedAt;
		this.alphaNumericCode = alphaNumericCode;
	}

	public ArrayList<AttendanceRecord> getAttendance() {
		return attendance;
	}

	public String getClassID() {
		return classID;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public LocalDateTime getStartedAt() {
		return startedAt;
	}

	public String getAlphaNumericCode() {
		return alphaNumericCode;
	}

	public Image getQrCodeImage() {
		return qrCodeImage;
	}

	public void setAttendance(ArrayList<AttendanceRecord> attendance) {
		this.attendance = attendance;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}

	public void setAlphaNumericCode(String alphaNumericCode) {
		this.alphaNumericCode = alphaNumericCode;
	}



	public void generateQRCode() {
		String data = "google.com";
		int sizeX = 100;
		int sizeY = 100;

		try {
			String apiUrl = "https://api.qrserver.com/v1/create-qr-code/?data=" + URLEncoder.encode(data, "UTF-8")
					+ "&size=" + sizeX + "x" + sizeY;

			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				Image qrCodeImage = ImageIO.read(connection.getInputStream());

				File outputfile = new File("QRCodeNew.png");
				ImageIO.write((RenderedImage) qrCodeImage, "png", outputfile);

				uploadImageToFirebaseStorage(outputfile);
			} else {
				throw new RuntimeException("Failed to fetch QR code image from the API");
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void uploadImageToFirebaseStorage(File file) {
		try {
			Bucket bucket = StorageSingleton.get();
			String fileName = "QRCodeNew.png";
			Blob blob = bucket.create(fileName, Files.readAllBytes(file.toPath()));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}