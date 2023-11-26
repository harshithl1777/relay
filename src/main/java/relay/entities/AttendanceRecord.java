package relay.entities;

import java.util.ArrayList;

public class AttendanceRecord {
    private String courseCode;
    private ArrayList<String> studentsPresent;
    private Student student;

    public AttendanceRecord(String courseCode, ArrayList<String> studentsPresent, Student student) {
        this.courseCode = courseCode;
        this.studentsPresent = studentsPresent;
        this.student = student;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public ArrayList<String> getStudentsPresent() {
        return studentsPresent;
    }

    public void setStudentsPresent(ArrayList<String> studentsPresent) {
        this.studentsPresent = studentsPresent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
