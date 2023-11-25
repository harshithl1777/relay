package entity;

public class Session {
    String courseCode;
    String date;
    String time;
    Instructor instructor;
    String section;
    public Session(String courseCode, String date, String time, Instructor instructor, String section) {
        this.courseCode = courseCode;
        this.date = date;
        this.time = time;
        this.instructor = instructor;
        this.section = section;
    }
}
