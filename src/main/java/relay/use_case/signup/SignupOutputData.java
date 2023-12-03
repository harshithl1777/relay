package relay.use_case.signup;

public class SignupOutputData {
	private String instructorID;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private boolean useCaseSuccess;
	private String errorMessage;

	public SignupOutputData(String instructorID, String firstName, String lastName, String emailAddress) {
		this.instructorID = instructorID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.useCaseSuccess = true;
	}

	public SignupOutputData(String errorMessage) {
		this.useCaseSuccess = false;
		this.errorMessage = errorMessage;
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

	public boolean getUseCaseSuccess() {
		return useCaseSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
