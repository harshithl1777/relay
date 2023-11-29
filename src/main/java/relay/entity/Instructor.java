package relay.entity;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
	private String firstName;
	private String lastName;
	private String email;
	private List<Course> courses;

	// Constructor for Instructor
	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.courses = new ArrayList<>();
	}

	// Getter for courses
	public List<Course> getCourses() {
		return courses;
	}

	// Method to add a course to the instructor's list of courses
	public void addCourse(Course course) {
		courses.add(course);
	}

	// Implementing methods from the User interface

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
}
