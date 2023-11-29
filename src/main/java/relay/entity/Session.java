package relay.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Session {
	private ArrayList<AttendanceRecord> attendance;
	private String classID;
	private SessionCode sessionCode;
	private Instructor instructor;
	private LocalDateTime startedAt;

	public Session(ArrayList<AttendanceRecord> attendance, String classID, SessionCode sessionCode,
			Instructor instructor,
			LocalDateTime startedAt) {
		this.attendance = attendance;
		this.classID = classID;
		this.sessionCode = sessionCode;
		this.instructor = instructor;
		this.startedAt = startedAt;
	}

	public ArrayList<AttendanceRecord> getAttendance() {
		return attendance;
	}

	public String getClassID() {
		return classID;
	}

	public SessionCode getSessionCode() {
		return sessionCode;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public LocalDateTime getStartedAt() {
		return startedAt;
	}

	public void setAttendance(ArrayList<AttendanceRecord> attendance) {
		this.attendance = attendance;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public void setSessionCode(SessionCode sessionCode) {
		this.sessionCode = sessionCode;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}
}
