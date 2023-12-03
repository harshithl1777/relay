package relay.use_case.create_course;

public class CreateCourseInputData {
	private String courseName;
	private String instructorID;

    public CreateCourseInputData(String courseName, String instructorID) {
        this.courseName = courseName;
        this.instructorID = instructorID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorID() {
        return instructorID;
    }
}
