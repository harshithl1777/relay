package relay.entities;

import java.util.ArrayList;

public class AttendanceRecord {
    private Student student;

    public AttendanceRecord(String courseCode, ArrayList<String> studentsPresent, Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
