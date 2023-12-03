package relay.interface_adapter.signup;

import org.springframework.http.HttpStatus;

import relay.interface_adapter.State;

public class SignupState implements State {
    private String instructorID;
    private String sessionID;
    private String lastName;
    private String emailAddress;
    private String errorMessage;
    private HttpStatus statusCode;

    public SignupState(SignupState copyState) {
        this.instructorID = copyState.instructorID;
        this.firstName = copyState.firstName;
        this.lastName = copyState.lastName;
        this.emailAddress = copyState.emailAddress;
        this.errorMessage = copyState.errorMessage;
        this.statusCode = copyState.statusCode;
    }

    public SignupState() {
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
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
    @Override
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
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
    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
