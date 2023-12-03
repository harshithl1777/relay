package relay.use_case.start_session;

public class StartSessionInputData {
	final private String courseID;
	final private String instructorID;

	public StartSessionInputData(String courseID, String instructorID) {
		this.courseID = courseID;
		this.instructorID = instructorID;
	}

	public String getCourseID() {
		return courseID;
	}

	public String getInstructorID() {
		return instructorID;
	}
}
