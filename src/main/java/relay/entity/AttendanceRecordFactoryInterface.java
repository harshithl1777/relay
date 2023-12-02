package relay.entity;

import java.util.Map;

public interface AttendanceRecordFactoryInterface {
	AttendanceRecord createAttendanceRecord(String studentFirstName, String studentLastName,
			String studentID,
			String studentEmailAddress);

	AttendanceRecord createAttendanceRecordFromMap(Map<String, Object> recordMap);
}
