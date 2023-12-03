package relay.use_case.show_courses;

import relay.entity.Course;

import java.util.ArrayList;

public interface ShowCourseDataAccessInterface {

    ArrayList<Course> getCoursesByInstructor(String InstructorID);
}
