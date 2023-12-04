package relay.unit.interface_adapter;

import org.junit.jupiter.api.Test;
import relay.interface_adapter.start_session.StartSessionState;

public class StartSessionStateTest {
    @Test
    public void testGetCourseID() {
        StartSessionState startSessionState = new StartSessionState();
        startSessionState.setCourseID("123");
        assert startSessionState.getCourseID().equals("123");
    }
    @Test
    public void testGetCourseName() {
        StartSessionState startSessionState = new StartSessionState();
        startSessionState.setCourseID("HHHH");
        assert startSessionState.getCourseID().equals("HHHH");
    }
    @Test
    public void testGetInstructorID() {
        StartSessionState startSessionState = new StartSessionState();
        startSessionState.setInstructorID("123");
        assert startSessionState.getInstructorID().equals("123");
    }
    @Test
    public void testGetErrorMessage() {
        StartSessionState startSessionState = new StartSessionState();
        startSessionState.setErrorMessage("error");
        assert startSessionState.getErrorMessage().equals("error");
    }
    @Test
    public void testGetStatusCode() {
        StartSessionState startSessionState = new StartSessionState();
        startSessionState.setStatusCode(null);
        assert startSessionState.getStatusCode() == null;
    }
    @Test
    public void testSetCourseID() {
        StartSessionState startSessionState = new StartSessionState();
        startSessionState.setCourseID("123");
        assert startSessionState.getCourseID().equals("123");
    }
    @Test
    public void testSetCourseName() {
        StartSessionState startSessionState = new StartSessionState();
        startSessionState.setCourseID("HHHH");
        assert startSessionState.getCourseID().equals("HHHH");
    }
    @Test
    public void testSetInstructorID() {
        StartSessionState startSessionState = new StartSessionState();
        startSessionState.setInstructorID("123");
        assert startSessionState.getInstructorID().equals("123");
    }
    @Test
    public void testSetErrorMessage() {
        StartSessionState startSessionState = new StartSessionState();
        startSessionState.setErrorMessage("error");
        assert startSessionState.getErrorMessage().equals("error");
    }
    @Test
    public void testSetStatusCode() {
        StartSessionState startSessionState = new StartSessionState();
        startSessionState.setStatusCode(null);
        assert startSessionState.getStatusCode() == null;
    }
}
