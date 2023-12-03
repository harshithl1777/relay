package relay.show_courses;

import relay.interface_adapter.State;
import relay.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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