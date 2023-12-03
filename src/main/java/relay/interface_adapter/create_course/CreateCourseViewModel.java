package relay.interface_adapter.create_course;

import relay.interface_adapter.State;
import relay.interface_adapter.ViewModel;

public class CreateCourseViewModel implements ViewModel {
    private State state = new CreateCourseState();

    public CreateCourseViewModel() {
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
