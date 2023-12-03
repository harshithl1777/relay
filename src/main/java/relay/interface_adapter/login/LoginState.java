package relay.interface_adapter.login;

import org.springframework.http.HttpStatus;

import relay.interface_adapter.State;

public class LoginState implements State {
    private String instructorID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String errorMessage;
    private HttpStatus statusCode;

    public LoginState(LoginState copyState) {
        this.instructorID = copyState.instructorID;
        this.firstName = copyState.firstName;
        this.lastName = copyState.lastName;
        this.emailAddress = copyState.emailAddress;
        this.errorMessage = copyState.errorMessage;
        this.statusCode = copyState.statusCode;
    }

    public LoginState() {
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

    public String getErrorMessage() {
        return errorMessage;
    }

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

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
