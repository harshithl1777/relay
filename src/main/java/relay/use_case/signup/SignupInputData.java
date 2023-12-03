package relay.use_case.signup;

public class SignupInputData {

	final private String firstName;
	final private String lastName;
	final private String emailAddress;

	public SignupInputData(String firstName, String lastName, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
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

}
