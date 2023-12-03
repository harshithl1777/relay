package relay.interface_adapter.start_session;

import org.springframework.http.HttpStatus;

import relay.interface_adapter.State;

import java.sql.Timestamp;

public class StartSessionState implements State {
    private String instructorID;
    private String sessionID;
    private String courseID;
    private Timestamp createdAt;
    private String errorMessage;
    private HttpStatus statusCode;

    public StartSessionState(StartSessionState copyState, String sessionID, String courseID, Timestamp createdAt) {
        this.instructorID = copyState.instructorID;
        this.errorMessage = copyState.errorMessage;
        this.statusCode = copyState.statusCode;
        this.sessionID = copyState.sessionID;
        this.courseID = copyState.courseID;
        this.createdAt = copyState.createdAt;
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

    public Timestamp getCreatedAt() {
        return createdAt;
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

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
