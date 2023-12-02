package relay.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public record AttendanceRecord(String studentFirstName, String studentLastName, String studentID,
		String studentEmailAddress, Timestamp createdAt) {

	public AttendanceRecord {
		Objects.requireNonNull(studentFirstName);
		Objects.requireNonNull(studentLastName);
		Objects.requireNonNull(studentID);
		Objects.requireNonNull(studentEmailAddress);
		Objects.requireNonNull(createdAt);
	}

	public Map<String, Object> convertToMap() {
		Map<String, Object> attendanceRecordMap = new HashMap<>();
		attendanceRecordMap.put("studentID", studentID);
		attendanceRecordMap.put("studentEmailAddress", studentEmailAddress);
		attendanceRecordMap.put("studentFirstName", studentFirstName);
		attendanceRecordMap.put("studentLastName", studentLastName);
		attendanceRecordMap.put("createdAt", createdAt);
		return attendanceRecordMap;
	}

}
