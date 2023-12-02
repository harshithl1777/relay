package relay.entity;

import java.util.List;
import java.util.ArrayList;

public class Course {
	private String courseID;
	private String courseName;
	private String instructorID;
	private List<AttendanceRecord> history;

	public Course(String courseName, String instructorID) {
		this.courseName = courseName;
		this.instructorID = instructorID;
		this.history = new ArrayList<>();
	}

	public String getCourseID() {
		return courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getInstructorID() {
		return instructorID;
	}

	public List<AttendanceRecord> getHistory() {
		return history;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}

	public void setHistory(List<AttendanceRecord> history) {
		this.history = history;
	}

	public void appendHistory(List<AttendanceRecord> newRecords) {
		this.history.addAll(newRecords);
	}
}
