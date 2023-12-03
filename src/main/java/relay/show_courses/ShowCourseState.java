package relay.show_courses;

import java.util.ArrayList;
import java.util.List;

public class ShowCourseState {
    private List<String> courseNames;

    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }
    public List<String> getCourseNames() {
        return courseNames;
    }
}
