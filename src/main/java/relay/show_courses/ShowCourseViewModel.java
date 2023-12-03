package relay.show_courses;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShowCourseViewModel extends ViewModel {

    private ShowCourseState state = new ShowCourseState();

    public ShowCourseViewModel() {
        super("show course");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ShowCourseState getState() {
        return state;
    }
    public void setState (ShowCourseState state) {
        this.state = state;

    }
}