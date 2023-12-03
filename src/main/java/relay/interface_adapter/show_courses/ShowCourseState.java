package relay.interface_adapter.show_courses;

import org.springframework.http.HttpStatus;
import relay.entity.AttendanceRecord;
import relay.interface_adapter.State;

import java.util.ArrayList;
import java.util.List;

public class ShowCourseState implements State {
    private String courseName;

    private String courseID;

    private List<String> courses;
    private ArrayList<AttendanceRecord> history;
    private String instructorID;
    private String errorMessage;
    private HttpStatus statusCode;

    public ShowCourseState(ShowCourseState copyState) {
        this.instructorID = copyState.instructorID;
        this.errorMessage = copyState.errorMessage;
        this.statusCode = copyState.statusCode;
        this.courseID = copyState.courseID;
        this.courseName = copyState.courseName;
        this.history = copyState.history;
        this.courses = copyState.courses;
    }

    public ShowCourseState() {
    }

    public String getInstructorID() {
        return instructorID;
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


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public ArrayList<AttendanceRecord> getHistory() {
        return history;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setHistory(List<AttendanceRecord> history) {
        history = history;
    }

    public void appendHistory(List<AttendanceRecord> newRecords) {
        history.addAll(newRecords);
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public List<String> getCourses() {
        return courses;
    }
}
