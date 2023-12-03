package relay.interface_adapter.start_session;

import org.springframework.http.HttpStatus;
import relay.entity.AttendanceRecord;
import relay.interface_adapter.State;

import java.sql.Timestamp;
import java.util.List;

public class StartSessionState implements State {
	private String instructorID;
	private String sessionID;
	private String courseID;
	private Timestamp startedAt;
	private String sessionCode;
	private List<AttendanceRecord> attendance;
	private String errorMessage;
	private HttpStatus statusCode;

	public StartSessionState(StartSessionState copyState, String sessionID, String courseID, Timestamp startedAt,
			List<AttendanceRecord> attendance, String sessionCode) {
		this.instructorID = copyState.instructorID;
		this.errorMessage = copyState.errorMessage;
		this.statusCode = copyState.statusCode;
		this.sessionID = copyState.sessionID;
		this.courseID = copyState.courseID;
		this.startedAt = copyState.startedAt;
		this.attendance = copyState.attendance;
		this.sessionCode = sessionCode;
	}

	public StartSessionState() {
	}

	public String getInstructorID() {
		return instructorID;
	}

	public String getCourseID() {
		return courseID;
	}

	public String getSessionID() {
		return sessionID;
	}

	public Timestamp getStartedAt() {
		return startedAt;
	}

	public List<AttendanceRecord> getAttendance() {
		return attendance;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void setStartedAt(Timestamp startedAt) {
		this.startedAt = startedAt;
	}

	public void setAttendance(List<AttendanceRecord> attendance) {
		this.attendance = attendance;
	}

	@Override
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}
}
