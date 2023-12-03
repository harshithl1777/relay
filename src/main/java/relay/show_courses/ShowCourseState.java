package relay.show_courses;

import java.util.List;

public class ShowCourseState {
    private List<String> courseNames;

    // Default constructor or constructor with no arguments
    public ShowCourseState() {
        // Initialize the list or perform any other necessary setup
    }


    // Constructor that accepts course names
    public ShowCourseState(List<String> courseNames) {
        this.courseNames = courseNames;
    }


    // Getter and setter methods
    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }

    public List<String> getCourseNames() {
        return courseNames;
    }
}
