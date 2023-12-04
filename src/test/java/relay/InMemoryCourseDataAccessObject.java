package relay;

import relay.entity.Course;
import relay.exceptions.ResourceNotFoundException;
import relay.use_case.create_course.CreateCourseCourseDataAccessInterface;
import relay.use_case.start_session.StartSessionCourseDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCourseDataAccessObject implements CreateCourseCourseDataAccessInterface, StartSessionCourseDataAccessInterface {
    private final Map<String, Course> courses = new HashMap<>();

    public Course getCourseByID(String courseID) throws ResourceNotFoundException {
        for (Course course : courses.values()) {
            if (course.getCourseID().equals(courseID)) {
                return course;
            }
        }
        throw new ResourceNotFoundException("No such instructor exists");
    }

    @Override
    public void save(Course course) {
        courses.put(course.getCourseID(), course);
    }

    @Override
    public boolean exists(String courseID) {
        return courses.containsKey(courseID);
    }
}