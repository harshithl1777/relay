package relay.interface_adapter.LogAttendance;

import relay.interface_adapter.State;
import relay.interface_adapter.ViewModel;

public class LogAttendanceViewModel implements ViewModel {

    private State state = new LogAttendanceState();

    public LogAttendanceViewModel() {
        super();
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;

    }
}