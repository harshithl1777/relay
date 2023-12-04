package relay.unit.interface_adapter;

import org.junit.jupiter.api.Test;
import relay.interface_adapter.show_courses.ShowCourseState;

public class ShowCourseStateTest {
    @Test
    public void testGetCourseName() {
        ShowCourseState showCoursesState = new ShowCourseState();
        showCoursesState.setCourses(null);
        showCoursesState.getCourses();
        assert showCoursesState.getCourses() == null;
    }
    @Test
    public void testGetErrorMessage() {
        ShowCourseState showCoursesState = new ShowCourseState();
        showCoursesState.setErrorMessage("error");
        showCoursesState.getErrorMessage();
        assert showCoursesState.getErrorMessage().equals("error");
    }
    @Test
    public void testGetStatusCode() {
        ShowCourseState showCoursesState = new ShowCourseState();
        showCoursesState.setStatusCode(null);
        showCoursesState.getStatusCode();
        assert showCoursesState.getStatusCode() == null;
    }
    @Test
    public void testSetCourseName() {
        ShowCourseState showCoursesState = new ShowCourseState();
        showCoursesState.setCourses(null);
        assert showCoursesState.getCourses() == null;
    }
    @Test
    public void testSetErrorMessage() {
        ShowCourseState showCoursesState = new ShowCourseState();
        showCoursesState.setErrorMessage("error");
        assert showCoursesState.getErrorMessage().equals("error");
    }
    @Test
    public void testSetStatusCode() {
        ShowCourseState showCoursesState = new ShowCourseState();
        showCoursesState.setStatusCode(null);
        assert showCoursesState.getStatusCode() == null;
    }
}
