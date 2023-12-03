package relay.use_case.start_session;

import java.sql.Timestamp;
import java.util.List;

import relay.entity.AttendanceRecord;

public class StartSessionOutputData {

	private boolean useCaseSuccess;
	private String errorMessage;
	private String sessionID;
	private String courseID;
	private String instructorID;
	private Timestamp startedAt;
	private String sessionCode;
	private List<AttendanceRecord> attendance;

	public StartSessionOutputData(String courseID, String instructorID, Timestamp startedAt, String sessionCode,
			String sessionID, List<AttendanceRecord> attendance) {
		this.useCaseSuccess = true;
		this.courseID = courseID;
		this.instructorID = instructorID;
		this.startedAt = startedAt;
		this.sessionCode = sessionCode;
		this.sessionID = sessionID;
		this.attendance = attendance;
	}

	public StartSessionOutputData(String errorMessage) {
		this.useCaseSuccess = false;
		this.errorMessage = errorMessage;
	}

	public String getInstructorID() {
		return instructorID;
	}

	public Timestamp getStartedAt() {
		return startedAt;
	}

	public String getSessionID() {
		return sessionID;
	}

	public String getCourseID() {
		return courseID;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean getUseCaseSuccess() {
		return useCaseSuccess;
	}

	public String getSessionCode() {
		return sessionCode;
	}

	public List<AttendanceRecord> getAttendance() {
		return attendance;
	}
}
