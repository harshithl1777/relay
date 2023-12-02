package relay.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionFactory implements SessionFactoryInterface {

	public Session createSession(String courseID, String instructorID) {
		List<AttendanceRecord> blankAttendance = new ArrayList<>();
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		Session session = new Session(blankAttendance, courseID, instructorID, currentTimestamp);
		return session;
	}

	public Session createSessionFromMap(Map<String, Object> sessionMap) {
		List<Map<String, Object>> attendanceMapsList = (List<Map<String, Object>>) sessionMap.get("attendance");
		String courseID = (String) sessionMap.get("courseID");
		String instructorID = (String) sessionMap.get("instructorID");
		com.google.cloud.Timestamp startedAtGoogle = (com.google.cloud.Timestamp) sessionMap.get("startedAt");
		Timestamp startedAtJavaSQL = startedAtGoogle.toSqlTimestamp();

		List<AttendanceRecord> attendance = new ArrayList<>();
		AttendanceRecordFactoryInterface attendanceRecordFactory = new AttendanceRecordFactory();
		attendanceMapsList.forEach(
				(recordMap) -> attendance.add(attendanceRecordFactory.createAttendanceRecordFromMap(recordMap)));
		Session session = new Session(attendance, courseID, instructorID, startedAtJavaSQL);
		return session;
	}

}
