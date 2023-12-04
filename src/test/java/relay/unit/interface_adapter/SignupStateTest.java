package relay.unit.interface_adapter;

import org.junit.jupiter.api.Test;
import relay.interface_adapter.signup.SignupState;

public class SignupStateTest {
    @Test
    public void testGetFirstName() {
        SignupState signupState = new SignupState();
        signupState.setFirstName("John");
        assert signupState.getFirstName().equals("John");
    }
    @Test
    public void testGetLastName() {
        SignupState signupState = new SignupState();
        signupState.setLastName("Doe");
        assert signupState.getLastName().equals("Doe");
    }
    @Test
    public void testGetInstructorID() {
        SignupState signupState = new SignupState();
        signupState.setInstructorID("123");
        assert signupState.getInstructorID().equals("123");
    }
    @Test
    public void testGetEmailAddress() {
        SignupState signupState = new SignupState();
        signupState.setEmailAddress("john@gmail.com");
        assert signupState.getEmailAddress().equals("john@gmail.com");
    }
    @Test
    public void testGetErrorMessage() {
        SignupState signupState = new SignupState();
        signupState.setErrorMessage("error");
        assert signupState.getErrorMessage().equals("error");
    }
    @Test
    public void testGetStatusCode() {
        SignupState signupState = new SignupState();
        signupState.setStatusCode(null);
        assert signupState.getStatusCode() == null;
    }
    @Test
    public void testSetFirstName() {
        SignupState signupState = new SignupState();
        signupState.setFirstName("John");
        assert signupState.getFirstName().equals("John");
    }
    @Test
    public void testSetLastName() {
        SignupState signupState = new SignupState();
        signupState.setLastName("Doe");
        assert signupState.getLastName().equals("Doe");
    }
    @Test
    public void testSetInstructorID() {
        SignupState signupState = new SignupState();
        signupState.setInstructorID("123");
        assert signupState.getInstructorID().equals("123");
    }
    @Test
    public void testSetEmailAddress() {
        SignupState signupState = new SignupState();
        signupState.setEmailAddress("john@gmail.com");
        assert signupState.getEmailAddress().equals("john@gmail.com");
    }
    @Test
    public void testSetErrorMessage() {
        SignupState signupState = new SignupState();
        signupState.setErrorMessage("error");
        assert signupState.getErrorMessage().equals("error");
    }
    @Test
    public void testSetStatusCode() {
        SignupState signupState = new SignupState();
        signupState.setStatusCode(null);
        assert signupState.getStatusCode() == null;
    }
}
