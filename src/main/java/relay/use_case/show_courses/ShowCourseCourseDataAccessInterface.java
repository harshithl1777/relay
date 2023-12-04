package relay.use_case.show_courses;

import relay.entity.Course;

import java.util.ArrayList;

public interface ShowCourseCourseDataAccessInterface {

	ArrayList<Course> getCoursesByInstructor(String InstructorID);
}
