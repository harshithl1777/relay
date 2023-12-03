package relay.interface_adapter.LogAttendance;

import org.springframework.http.HttpStatus;
import relay.interface_adapter.State;

import java.sql.Timestamp;

public class LogAttendanceState implements State{
    private String studentFirstName;
    private String studentLastName;
    private String studentID;
    private String studentEmailAddress;
    private Timestamp createdAt;
    private String errorMessage;
    private HttpStatus statusCode;

    public LogAttendanceState(LogAttendanceState copyState) {
        this.studentFirstName = copyState.studentFirstName;
        this.studentLastName = copyState.studentLastName;
        this.studentID = copyState.studentID;
        this.studentEmailAddress = copyState.studentEmailAddress;
        this.createdAt = copyState.createdAt;
        this.errorMessage = copyState.errorMessage;
        this.statusCode = copyState.statusCode;
    }

    public LogAttendanceState() {
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentEmailAddress() {
        return studentEmailAddress;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setStudentEmailAddress(String studentEmailAddress) {
        this.studentEmailAddress = studentEmailAddress;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public HttpStatus getStatusCode() {
        return statusCode;
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
