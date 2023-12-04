package relay.use_case.create_course;

import relay.entity.Course;

public interface CreateCourseDataAccessInterface {
    public void save(Course course);
}
