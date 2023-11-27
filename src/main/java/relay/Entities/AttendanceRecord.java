package relay.Entities;
import java.util.Date;

public class AttendanceRecord {
    private String name;
    private String studentID;
    private int timestamp;
    private int startedAt;

    // Constructor for AttendanceRecord
    public AttendanceRecord(String name, String studentID, int timestamp, int startedAt) {
        this.name = name;
        this.studentID = studentID;
        this.timestamp = timestamp;
        this.startedAt = startedAt;
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

    public int isStartedAt() {
        return startedAt;
    }


}