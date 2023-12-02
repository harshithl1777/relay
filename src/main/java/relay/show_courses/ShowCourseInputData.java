package relay.show_courses;

public class ShowCourseInputData {
    final private String InstructorID;

    public ShowCourseInputData(String instructorID) {
        InstructorID = instructorID;
    }

    public String getInstructorID() {
        return InstructorID;
    }
}
