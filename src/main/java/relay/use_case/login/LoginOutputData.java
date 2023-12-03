package relay.use_case.login;

/**
 * Data class representing the output data for the login use case.
 */
public class LoginOutputData {
    private String instructorID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String errorMessage;

    private String useCaseSuccess;


    public String getUseCaseSuccess() {
        return useCaseSuccess;
    }

    public void setUseCaseSuccess(String useCaseSuccess) {
        this.useCaseSuccess = useCaseSuccess;
    }


    public LoginOutputData(String instructorID, String firstName, String lastName, String emailAddress) {
        this.instructorID = instructorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public LoginOutputData(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
