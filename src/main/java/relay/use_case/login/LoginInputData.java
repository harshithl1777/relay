package relay.use_case.login;

/**
 * Data class representing the input data for the login use case.
 */
public class LoginInputData {
    private String instructorEmailAddress;

    public LoginInputData(String instructorEmailAddress) {
        this.instructorEmailAddress = instructorEmailAddress;
    }

    public String getInstructorEmailAddress() {
        return instructorEmailAddress;
    }

    public void setInstructorEmailAddress(String instructorID) {
        this.instructorEmailAddress = instructorID;
    }

}
