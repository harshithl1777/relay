package relay.show_courses;

import relay.entity.Course;

import java.util.ArrayList;

public interface FirebaseCourseDataObjectInterface {

    ArrayList<Course> getCoursesByInstructor(String InstructorID);
}
