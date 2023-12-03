package relay.interface_adapter.show_courses;

import relay.interface_adapter.State;
import relay.interface_adapter.ViewModel;
import relay.interface_adapter.show_courses.ShowCourseState;

public class ShowCourseViewModel implements ViewModel {

    private State state = new ShowCourseState();


    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {

    }

    public void setState (ShowCourseState state) {
        this.state = state;

    }
}