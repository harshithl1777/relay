package relay.use_case.start_session;

import java.sql.Timestamp;

public class StartSessionInputData {
    final private String courseID;
    final private String instructorID;

    final private Timestamp startedAt;


    public StartSessionInputData(String courseID, String instructorID, Timestamp startedAt) {
        this.courseID = courseID;
        this.instructorID = instructorID;
        this.startedAt = startedAt;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }


}
