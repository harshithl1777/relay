package relay.entity;

import java.util.List;

public class Course {
	private String courseID;
	private String courseName;
	private Instructor instructor;
	private List<AttendanceRecord> history;

	public Course(String courseID, String courseName, Instructor instructor) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.instructor = instructor;
		this.history = new List<>();
	}

	public String getCourseID() {
		return courseID;
	}

	public String getCourseName(){return courseName; }

	public Instructor getInstructor() {
		return instructor;
	}

	public List<AttendanceRecord> getHistory() {
		return history;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void setCourseName(String courseName){this.courseName = courseName;}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public void setHistory(List<AttendanceRecord> history) {
		this.history = history;
	}

	public void appendHistory(List<AttendanceRecord> newRecords) {
		this.history.addAll(newRecords);
	}
}
