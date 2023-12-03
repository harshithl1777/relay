package relay.use_case.login;

public class LoginInputData {
    private String instructorEmailAddress;

    public LoginInputData(String socialToken, String instructorEmailAddress) {
        this.instructorEmailAddress = instructorEmailAddress;
    }

    public String getInstructorEmailAddress() {
        return instructorEmailAddress;
    }

    public void setInstructorEmailAddress(String instructorID) {
        this.instructorEmailAddress = instructorID;
    }

}
