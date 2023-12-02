package relay.use_case.signup;

public class SignupOutputData {
	private final String instructorID;
	private final String firstName;
	private final String lastName;
	private final String emailAddress;

	public SignupOutputData(String instructorID, String firstName, String lastName, String emailAddress) {
		this.instructorID = instructorID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	public String getInstructorID() {
		return instructorID;
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
}
