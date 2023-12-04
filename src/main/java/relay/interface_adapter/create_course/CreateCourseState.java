package relay.interface_adapter.create_course;

import org.springframework.http.HttpStatus;
import relay.entity.AttendanceRecord;
import relay.interface_adapter.State;

import java.util.List;

public class CreateCourseState implements State {
    private String instructorID;
    private String courseName;
    private String courseID;
    private String errorMessage;
    private List<AttendanceRecord> history;
    private HttpStatus statusCode;

    public CreateCourseState(CreateCourseState copyState) {
        this.instructorID = copyState.instructorID;
        this.courseName = copyState.courseName;
        this.courseID = copyState.courseID;
        this.errorMessage = copyState.errorMessage;
        this.history = copyState.history;
        this.statusCode = copyState.statusCode;
    }

    public CreateCourseState() {
    }

    public String getInstructorID() {
        return instructorID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseID() {
        return courseID;
    }
    public List<AttendanceRecord> getHistory() {
        return history;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
    public void setHistory(List<AttendanceRecord> history) {this.history = history;
    }

    public void appendHistory(List<AttendanceRecord> newRecords) {
        this.history.addAll(newRecords);
    }
}
