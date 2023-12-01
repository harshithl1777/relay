package relay.entity;


public class Instructor {
	private String instructorID;
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

	public String getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}
}
