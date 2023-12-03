package relay.interface_adapter.start_session;

import relay.interface_adapter.State;
import relay.interface_adapter.ViewModel;

/**
 * ViewModel class representing the view model for the login operation.
 */
public class StartSessionViewModel implements ViewModel {

    private State state = new StartSessionState();
    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
