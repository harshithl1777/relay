package relay.unit.interface_adapter;

import org.junit.jupiter.api.Test;
import relay.interface_adapter.end_session.EndSessionState;

public class EndSessionStateTest {
    @Test
    public void testGetErrorMessage() {
        EndSessionState endSessionState = new EndSessionState();
        endSessionState.setErrorMessage("error");
        endSessionState.getErrorMessage();
        assert endSessionState.getErrorMessage().equals("error");
    }
    @Test
    public void testGetStatusCode() {
        EndSessionState endSessionState = new EndSessionState();
        endSessionState.setStatusCode(null);
        endSessionState.getStatusCode();
        assert endSessionState.getStatusCode() == null;
    }
    @Test
    public void testSetErrorMessage() {
        EndSessionState endSessionState = new EndSessionState();
        endSessionState.setErrorMessage("error");
        assert endSessionState.getErrorMessage().equals("error");
    }
    @Test
    public void testSetStatusCode() {
        EndSessionState endSessionState = new EndSessionState();
        endSessionState.setStatusCode(null);
        assert endSessionState.getStatusCode() == null;
    }
}
