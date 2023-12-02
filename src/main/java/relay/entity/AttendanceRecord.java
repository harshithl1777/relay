package relay.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public record AttendanceRecord(Student student, Timestamp createdAt) {

	public AttendanceRecord {
		Objects.requireNonNull(student);
		Objects.requireNonNull(createdAt);
	}

	public Map<String, Object> convertToMap() {
		Map<String, Object> attendanceRecordMap = new HashMap<>();
		attendanceRecordMap.put("studentID", student.studentID());
		attendanceRecordMap.put("studentEmailAddress", student.emailAddress());
		attendanceRecordMap.put("studentFirstName", student.firstName());
		attendanceRecordMap.put("studentLastName", student.lastName());
		return attendanceRecordMap;
	}

}
