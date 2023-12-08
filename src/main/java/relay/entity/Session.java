package relay.entity;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class Session {
	private List<AttendanceRecord> attendance;
	private String sessionID;
	private String courseID;
	private String instructorID;
	private Timestamp startedAt;
	private String alphaNumericCode;
	private Image qrCodeImage;

	private static final String QR_CODE_API_PATH = "https://api.qrserver.com/v1/create-qr-code/?data=%s&size=%dx%d";
	private static final String QR_CODE_REDIRECT_PATH = "https://relay-amsy.onrender.com/students?code=";
	private static final int QR_SIZE_X = 100;
	private static final int QR_SIZE_Y = 100;

	public Session(List<AttendanceRecord> attendance, String courseID, String instructorID,
			Timestamp startedAt) {
		this.attendance = attendance;
		this.courseID = courseID;
		this.instructorID = instructorID;
		this.startedAt = startedAt;
	}

	public List<AttendanceRecord> getAttendance() {
		return attendance;
	}

	public String getSessionID() {
		return sessionID;
	}

	public String getCourseID() {
		return courseID;
	}

	public String getInstructorID() {
		return instructorID;
	}

	public Timestamp getStartedAt() {
		return startedAt;
	}

	public String getAlphaNumericCode() {
		return alphaNumericCode;
	}

	public Image getQrCodeImage() {
		return qrCodeImage;
	}

	public void setAttendance(List<AttendanceRecord> attendance) {
		this.attendance = attendance;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}

	public void setStartedAt(Timestamp startedAt) {
		this.startedAt = startedAt;
	}

	public void setAlphaNumericCode(String alphanumCode) {
		this.alphaNumericCode = alphanumCode;
	}

	public void setQrCodeImage(Image qrCodeImage) {
		this.qrCodeImage = qrCodeImage;
	}

	public void generateQRCode() {
		try {
			if (alphaNumericCode == null)
				throw new NullPointerException();

			String QRCodeData = URLEncoder.encode(QR_CODE_REDIRECT_PATH + alphaNumericCode, "UTF-8");
			String apiURL = String.format(QR_CODE_API_PATH, QRCodeData, QR_SIZE_X, QR_SIZE_Y);

			URL url = new URI(apiURL).toURL();
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			Image qrCodeImage = ImageIO.read(connection.getInputStream());
			setQrCodeImage(qrCodeImage);

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> convertToMap() {
		Map<String, Object> sessionMap = new HashMap<>();

		List<Map<String, Object>> attendanceRecordMaps = new ArrayList<>();
		for (AttendanceRecord record : attendance) {
			attendanceRecordMaps.add(record.convertToMap());
		}

		sessionMap.put("instructorID", instructorID);
		sessionMap.put("courseID", courseID);
		sessionMap.put("startedAt", startedAt);
		sessionMap.put("sessionCode", alphaNumericCode);
		sessionMap.put("attendance", attendanceRecordMaps);

		return sessionMap;
	}
}
