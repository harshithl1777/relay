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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Session {
	private static final String QR_DATA = "google.com";
	private static final int QR_SIZE_X = 100;
	private static final int QR_SIZE_Y = 100;
	private static final String apiUrl;

	static {
		try {
			apiUrl = String.format("https://api.qrserver.com/v1/create-qr-code/?data=%s&size=%dx%d",
					URLEncoder.encode(QR_DATA, "UTF-8"), QR_SIZE_X, QR_SIZE_Y);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

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
		try {
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
		} catch (IOException | RuntimeException e) {
			System.out.println(e);
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