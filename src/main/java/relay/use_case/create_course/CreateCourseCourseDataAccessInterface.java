package relay.use_case.create_course;

import relay.entity.Course;

public interface CreateCourseCourseDataAccessInterface {
    public void save(Course course);
}
