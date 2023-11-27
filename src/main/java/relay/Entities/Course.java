package relay.Entities;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseId;
    private Instructor instructor;
    private List<AttendanceRecord> history; // ArrayList of AttendanceRecord objects

    // Constructor for Course
    public Course(int courseId, Instructor instructor) {
        this.courseId = courseId;
        this.instructor = instructor;
        this.history = new ArrayList<>();
    }

    // Getter and setter for history
    public List<AttendanceRecord> getHistory() {
        return history;
    }

    public void setHistory(List<AttendanceRecord> history) {
        this.history = history;
    }
}
