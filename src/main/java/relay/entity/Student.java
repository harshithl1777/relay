package relay.entity;

public class Student {
	private String firstName;
	private String lastName;
	private String studentID;
	private String emailAddress;

	public Student(String firstName, String lastName, String studentID, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		this.emailAddress = emailAddress;
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

	public String getStudentID() {
		return studentID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
