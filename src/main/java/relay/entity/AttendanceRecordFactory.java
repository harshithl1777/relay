package relay.entity;

import java.sql.Timestamp;
import java.util.Map;

public class AttendanceRecordFactory {
	public static AttendanceRecord createAttendanceRecord(String studentFirstName, String studentLastName,
			String studentID,
			String studentEmailAddress) {
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		return new AttendanceRecord(studentFirstName, studentLastName, studentID, studentEmailAddress,
				currentTimestamp);
	}

	public static AttendanceRecord createAttendanceRecordFromMap(Map<String, Object> recordMap) {
		String studentFirstName = (String) recordMap.get("studentFirstName");
		String studentLastName = (String) recordMap.get("studentLastName");
		String studentEmailAddress = (String) recordMap.get("studentEmailAddress");
		String studentID = (String) recordMap.get("studentID");
		com.google.cloud.Timestamp createdAtGoogle = (com.google.cloud.Timestamp) recordMap.get("createdAt");
		Timestamp createdAtJavaSQL = createdAtGoogle.toSqlTimestamp();

		return new AttendanceRecord(studentFirstName, studentLastName, studentID, studentEmailAddress,
				createdAtJavaSQL);
	}
}
