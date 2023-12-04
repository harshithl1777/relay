package relay.unit;

import relay.entity.Course;
import relay.entity.Instructor;
import relay.exceptions.ResourceAlreadyExistsException;
import relay.exceptions.ResourceNotFoundException;
import relay.use_case.create_course.CreateCourseCourseDataAccessInterface;
import relay.use_case.end_session.EndSessionCourseDataAccessInterface;
import relay.use_case.show_courses.ShowCourseCourseDataAccessInterface;
import relay.use_case.start_session.StartSessionCourseDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryCourseDataAccessObject implements CreateCourseCourseDataAccessInterface, StartSessionCourseDataAccessInterface, EndSessionCourseDataAccessInterface, ShowCourseCourseDataAccessInterface {
    private final Map<String, Course> courses = new HashMap<>();

    private static int nextID;

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
        if (course.getCourseID() != null) {
            courses.put(course.getInstructorID(), course);
        } else {
            setNextID();
            course.setCourseID(String.valueOf(nextID));
            courses.put(String.valueOf(nextID), course);
        }
    }

    private void setNextID() {
        while (courses.containsKey(String.valueOf(nextID))) {
            nextID++;
        }

    }

//    public void save(Instructor instructor) throws ResourceAlreadyExistsException {
//        if (instructor.getInstructorID() != null) {
//            instructors.put(instructor.getInstructorID(), instructor);
//        } else {
//            setNextID();
//            instructor.setInstructorID(String.valueOf(nextID));
//            instructors.put(String.valueOf(nextID), instructor);
//        }
//
//    }


    @Override
    public boolean exists(String courseID) {
        return courses.containsKey(courseID);
    }

    @Override
    public ArrayList<Course> getCoursesByInstructor(String instructorID) {
        ArrayList<Course> coursesRetrieved = new ArrayList<>();
        for (Course course : courses.values()) {
            if (course.getInstructorID().equals(instructorID)) {
                coursesRetrieved.add(course);
            }
        }
        return coursesRetrieved;
    }
}