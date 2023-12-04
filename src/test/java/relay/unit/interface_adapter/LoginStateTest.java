package relay.unit.interface_adapter;

import org.junit.jupiter.api.Test;
import relay.interface_adapter.login.LoginState;

public class LoginStateTest {
    @Test
    public void testGetFirstName() {
        LoginState loginState = new LoginState();
        loginState.setFirstName("John");
        assert loginState.getFirstName().equals("John");
    }
    @Test
    public void testGetLastName() {
        LoginState loginState = new LoginState();
        loginState.setLastName("Doe");
        assert loginState.getLastName().equals("Doe");
    }
    @Test
    public void testGetInstructorID() {
        LoginState loginState = new LoginState();
        loginState.setInstructorID("123");
        assert loginState.getInstructorID().equals("123");
    }
    @Test
    public void testGetEmailAddress() {
        LoginState loginState = new LoginState();
        loginState.setEmailAddress("john@gmail.com");
        assert loginState.getEmailAddress().equals("john@gmail.com");
    }
    @Test
    public void testGetErrorMessage() {
        LoginState loginState = new LoginState();
        loginState.setErrorMessage("error");
        assert loginState.getErrorMessage().equals("error");
    }
    @Test
    public void testGetStatusCode() {
        LoginState loginState = new LoginState();
        loginState.setStatusCode(null);
        assert loginState.getStatusCode() == null;
    }
    @Test
    public void testSetFirstName() {
        LoginState loginState = new LoginState();
        loginState.setFirstName("John");
        assert loginState.getFirstName().equals("John");
    }
    @Test
    public void testSetLastName() {
        LoginState loginState = new LoginState();
        loginState.setLastName("Doe");
        assert loginState.getLastName().equals("Doe");
    }
    @Test
    public void testSetInstructorID() {
        LoginState loginState = new LoginState();
        loginState.setInstructorID("123");
        assert loginState.getInstructorID().equals("123");
    }
    @Test
    public void testSetEmailAddress() {
        LoginState loginState = new LoginState();
        loginState.setEmailAddress("john@gmail.com");
        assert loginState.getEmailAddress().equals("john@gmail.com");
    }
    @Test
    public void testSetErrorMessage() {
        LoginState loginState = new LoginState();
        loginState.setErrorMessage("error");
        assert loginState.getErrorMessage().equals("error");
    }
    @Test
    public void testSetStatusCode() {
        LoginState loginState = new LoginState();
        loginState.setStatusCode(null);
        assert loginState.getStatusCode() == null;
    }
}
