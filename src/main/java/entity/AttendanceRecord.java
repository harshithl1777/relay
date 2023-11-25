package entity;

public class AttendanceRecord {
    String courseCode;
    String date;
    String time;
    Instructor instructor;
    String section;
    Session attendanceSession;
    public AttendanceRecord(String courseCode, String date, String time, Instructor instructor, String section, Session attendanceSession) {
        this.courseCode = courseCode;
        this.date = date;
        this.time = time;
        this.instructor = instructor;
        this.section = section;
        this.attendanceSession = attendanceSession;
    }
}
