package relay.entity;

import java.util.List;

public class InstructorFactory {

    public static Instructor createInstructor(String firstName, String lastName, String emailAddress) {
        return new Instructor(firstName, lastName, emailAddress);
    }

    public static Instructor createInstructorWithCourses(String firstName, String lastName, String emailAddress, List<Course> courses) {
        return new Instructor(firstName, lastName, emailAddress);
    }

}
