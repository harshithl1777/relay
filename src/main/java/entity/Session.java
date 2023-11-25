package entity;

import java.util.List;

public class Session {
    List<String> attendance;
    String classID;
    Integer Code;
    Instructor instructor;
    Integer startedAt;

    // Constructor
    public Session(List<String> attendance, String classID, Integer code, Instructor instructor, Integer startedAt) {
        this.attendance = attendance;
        this.classID = classID;
        Code = code;
        this.instructor = instructor;
        this.startedAt = startedAt;
    }
}