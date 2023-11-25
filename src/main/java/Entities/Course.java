package Entities;

public class Course {
    private int courseId;
    private Instructor instructor;
    private AttendanceRecord[] history; // Array of AttendanceRecord objects (to be created)

    // Constructor for Course
    public Course(int courseId, Instructor instructor) {
        this.courseId = courseId;
        this.instructor = instructor;
        // Initialize history array as needed
        // this.history = new AttendanceRecord[MAX_HISTORY_SIZE];
    }


    // Getter and setter for history
    public AttendanceRecord[] getHistory() {
        return history;
    }

    public void setHistory(AttendanceRecord[] history) {
        this.history = history;
    }
}
