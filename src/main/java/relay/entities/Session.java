package relay.entities;

import com.google.type.DateTime;

import java.util.ArrayList;

public class Session {
    ArrayList<AttendanceRecord> attendance;
    String classID;
    AttendanceCode code;
    Instructor instructor;
    DateTime startedAt;

    public Session(ArrayList<AttendanceRecord> attendance, String classID, AttendanceCode code, Instructor instructor, DateTime startedAt) {
        this.attendance = attendance;
        this.classID = classID;
        this.code = code;
        this.instructor = instructor;
        this.startedAt = startedAt;
    }
}