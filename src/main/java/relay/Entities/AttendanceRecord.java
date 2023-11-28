package relay.Entities;
import com.google.type.DateTime;

import java.util.Date;
import java.time.LocalDateTime;


public class AttendanceRecord {
    private String name;
    private String studentID;
    private int timestamp;
    private LocalDateTime createdAt;

    // Constructor for AttendanceRecord
    public AttendanceRecord(String name, String studentID, int timestamp, LocalDateTime createdAt) {
        this.name = name;
        this.studentID = studentID;
        this.timestamp = timestamp;
        this.createdAt = createdAt;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public LocalDateTime isCreatedAt() {
        return createdAt;
    }


}