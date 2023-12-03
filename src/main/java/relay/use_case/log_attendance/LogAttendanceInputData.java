package relay.use_case.log_attendance;


import java.sql.Timestamp;


public class LogAttendanceInputData {
    final private String sessionID;
    final private String studentFirstName;
    final private String studentLastName;
    final private String studentID;
    final private String studentEmail;
    final private Timestamp createdAt;

    public LogAttendanceInputData(String sessionID, String studentFirstName, String studentLastName, String studentID, String studentEmail, Timestamp createdAt) {
        this.sessionID = sessionID;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentID = studentID;
        this.studentEmail = studentEmail;
        this.createdAt = createdAt;
    }
    public String getSessionID() {
        return sessionID;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
