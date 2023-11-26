package relay.entities;

import java.util.ArrayList;

public class Session {
    ArrayList<AttendanceRecord> attendance;
    String classID;
    AttendanceCode code;
    Instructor instructor;
    Integer startedAt;

    public Session(ArrayList<AttendanceRecord> attendance, String classID, AttendanceCode code, Instructor instructor, Integer startedAt) {
        this.attendance = attendance;
        this.classID = classID;
        this.code = code;
        this.instructor = instructor;
        this.startedAt = startedAt;
    }
}