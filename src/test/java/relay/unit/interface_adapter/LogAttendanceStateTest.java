package relay.unit.interface_adapter;

import org.junit.jupiter.api.Test;
import relay.interface_adapter.log_attendance.LogAttendanceState;

public class LogAttendanceStateTest {
    @Test
    public void testGetCourseID() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setStudentID("zyx");
        logAttendanceState.getStudentID();
        assert logAttendanceState.getStudentID().equals("zyx");
    }
    @Test
    public void testGetCourseName() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setStudentFirstName("ztz");
        logAttendanceState.getStudentFirstName();
        assert logAttendanceState.getStudentFirstName().equals("ztz");
    }
    @Test
    public void testGetInstructorID() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setStudentLastName("zyx");
        logAttendanceState.getStudentLastName();
        assert logAttendanceState.getStudentLastName().equals("zyx");
    }
    @Test
    public void testGetHistory() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.getCreatedAt();
        assert logAttendanceState.getCreatedAt() == null;
    }
    @Test
    public void testGetErrorMessage() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setErrorMessage("error");
        logAttendanceState.getErrorMessage();
        assert logAttendanceState.getErrorMessage().equals("error");
    }
    @Test
    public void testGetStatusCode() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setStatusCode(null);
        logAttendanceState.getStatusCode();
        assert logAttendanceState.getStatusCode() == null;
    }
    @Test
    public void testSetCourseID() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setStudentID("zyx");
        assert logAttendanceState.getStudentID().equals("zyx");
    }
    @Test
    public void testSetCourseName() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setStudentFirstName("ztz");
        assert logAttendanceState.getStudentFirstName().equals("ztz");
    }
    @Test
    public void testSetInstructorID() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setStudentLastName("zyx");
        assert logAttendanceState.getStudentLastName().equals("zyx");
    }
    @Test
    public void testSetHistory() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setCreatedAt(null);
        assert logAttendanceState.getCreatedAt() == null;
    }
    @Test
    public void testSetErrorMessage() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setErrorMessage("error");
        assert logAttendanceState.getErrorMessage().equals("error");
    }
    @Test
    public void testSetStatusCode() {
        LogAttendanceState logAttendanceState = new LogAttendanceState();
        logAttendanceState.setStatusCode(null);
        assert logAttendanceState.getStatusCode() == null;
    }
}
