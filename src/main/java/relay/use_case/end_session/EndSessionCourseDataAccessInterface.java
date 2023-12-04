package relay.use_case.end_session;

import relay.entity.Course;
import relay.exceptions.ResourceNotFoundException;

public interface EndSessionCourseDataAccessInterface {
	void save(Course course) throws ResourceNotFoundException;

	Course getCourseByID(String courseID) throws ResourceNotFoundException;
}
