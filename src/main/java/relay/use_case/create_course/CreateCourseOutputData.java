package relay.use_case.create_course;

import relay.entity.AttendanceRecord;

import java.util.List;

public class CreateCourseOutputData {
    private String instructorID;
    private String courseName;
    private String courseID;
    private List<AttendanceRecord> history;
    private String errorMessage;
    private boolean useCaseSuccess;

    public CreateCourseOutputData(String instructorID, String courseName, String courseID, List<AttendanceRecord> history) {
        this.instructorID = instructorID;
        this.courseName = courseName;
        this.courseID = courseID;
        this.history = history;
    }

    public CreateCourseOutputData(String errorMessage) {
		this.useCaseSuccess = false;
		this.errorMessage = errorMessage;
	}

    public String getInstructorID() {
        return instructorID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<AttendanceRecord> getHistory() {
        return history;
    }

    public boolean getUseCaseSuccess() {
        return useCaseSuccess;
    }
}
