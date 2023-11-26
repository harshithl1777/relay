package entity;

import java.util.ArrayList;

public class Session {
    ArrayList<String> attendance;
    String classID;
    Integer Code;
    Instructor instructor;
    Integer startedAt;

    // Constructor
    public Session(ArrayList<String> attendance, String classID, Integer code, Instructor instructor, Integer startedAt) {
        this.attendance = attendance;
        this.classID = classID;
        Code = code;
        this.instructor = instructor;
        this.startedAt = startedAt;
    }
}