package relay.entity;

import java.util.ArrayList;

public class Instructor {
	private String firstName;
	private String lastName;
	private String emailAddress;
	private ArrayList<Course> courses;

	public Instructor(String firstName, String lastName, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.courses = new ArrayList<>();
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}
}
