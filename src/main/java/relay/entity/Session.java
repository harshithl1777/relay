package relay.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Session {
	ArrayList<AttendanceRecord> attendance;
	String classID;
	AttendanceCode code;
	Instructor instructor;
	LocalDateTime startedAt;

	public Session(ArrayList<AttendanceRecord> attendance, String classID, AttendanceCode code, Instructor instructor,
			LocalDateTime startedAt) {
		this.attendance = attendance;
		this.classID = classID;
		this.code = code;
		this.instructor = instructor;
		this.startedAt = startedAt;
	}
}
