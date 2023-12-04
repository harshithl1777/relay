package relay.use_case.create_course;

import relay.entity.Course;
import relay.exceptions.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCourseCourseDataAccessObject implements CreateCourseCourseDataAccessInterface {
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
}