package relay.use_case.start_session;

import java.sql.Timestamp;

public class StartSessionOutputData {

    private boolean useCaseSuccess;
    private String errorMessage;
    private String courseID;
    private String instructorID;
    private Timestamp startedAt;

    public StartSessionOutputData(boolean useCaseSuccess, String errorMessage, String courseID, String instructorID, Timestamp startedAt) {
        this.useCaseSuccess = true;
        this.courseID = courseID;
        this.instructorID = instructorID;
        this.startedAt = startedAt;
    }

    public StartSessionOutputData(String errorMessage) {
        this.useCaseSuccess = false;
        this.errorMessage = errorMessage;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean getUseCaseSuccess() {
        return useCaseSuccess;
    }
}
