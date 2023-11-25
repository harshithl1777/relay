package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AttendanceRecord {
    private String courseCode;
    private LocalDate date;
    private LocalDateTime time;
    private Instructor instructor;
    private String section;
    private Session attendanceSession;
    private List<String> studentsPresent;

    public AttendanceRecord(String courseCode, LocalDate date, LocalDateTime time, Instructor instructor, String section, Session attendanceSession, List<String> studentsPresent) {

        this.courseCode = courseCode;
        this.date = date;
        this.time = time;
        this.instructor = instructor;
        this.section = section;
        this.attendanceSession = attendanceSession;
        this.studentsPresent = studentsPresent;
    }

}
