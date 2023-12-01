package relay.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseID;
    private Instructor instructor;
    private List<AttendanceRecord> history;

    public Course(String courseID, Instructor instructor) {
        this.courseID = courseID;
        this.instructor = instructor;
        this.history = new ArrayList<>();
    }

    public String getCourseID() {
        return courseID;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<AttendanceRecord> getHistory() {
        return history;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setHistory(List<AttendanceRecord> history) {
        this.history = history;
    }

    public void appendHistory(List<AttendanceRecord> newRecords) {
        this.history.addAll(newRecords);
    }
}
