package relay.unit.interface_adapter;

import org.junit.jupiter.api.Test;
import relay.interface_adapter.create_course.CreateCourseState;

public class CreateCourseStateTest {
    @Test
    public void testGetCourseName() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setCourseName("CSC 110");
        createCourseState.getCourseName();
        assert createCourseState.getCourseName().equals("CSC 110");
    }
    @Test
    public void testGetCourseID() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setCourseID("zyx");
        createCourseState.getCourseID();
        assert createCourseState.getCourseID().equals("zyx");
    }
    @Test
    public void testGetInstructorID() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setInstructorID("ztz");
        createCourseState.getInstructorID();
        assert createCourseState.getInstructorID().equals("ztz");
    }
    @Test
    public void testGetHistory() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.getHistory();
        assert createCourseState.getHistory() == null;
    }
    @Test
    public void testGetErrorMessage() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setErrorMessage("error");
        createCourseState.getErrorMessage();
        assert createCourseState.getErrorMessage().equals("error");
    }
    @Test
    public void testGetStatusCode() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setStatusCode(null);
        createCourseState.getStatusCode();
        assert createCourseState.getStatusCode() == null;
    }
    @Test
    public void testSetCourseName() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setCourseName("CSC 110");
        assert createCourseState.getCourseName().equals("CSC 110");
    }
    @Test
    public void testSetCourseID() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setCourseID("zyx");
        assert createCourseState.getCourseID().equals("zyx");
    }
    @Test
    public void testSetInstructorID() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setInstructorID("ztz");
        assert createCourseState.getInstructorID().equals("ztz");
    }
    @Test
    public void testSetErrorMessage() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setErrorMessage("error");
        assert createCourseState.getErrorMessage().equals("error");
    }
    @Test
    public void testSetStatusCode() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setStatusCode(null);
        assert createCourseState.getStatusCode() == null;
    }
    @Test
    public void testCopyConstructor() {
        CreateCourseState createCourseState = new CreateCourseState();
        createCourseState.setCourseName("CSC 110");
        createCourseState.setCourseID("zyx");
        createCourseState.setInstructorID("ztz");
        createCourseState.setErrorMessage("error");
        createCourseState.setStatusCode(null);
        CreateCourseState copyState = new CreateCourseState(createCourseState);
        assert copyState.getCourseName().equals("CSC 110");
        assert copyState.getCourseID().equals("zyx");
        assert copyState.getInstructorID().equals("ztz");
        assert copyState.getErrorMessage().equals("error");
        assert copyState.getStatusCode() == null;
    }
}
