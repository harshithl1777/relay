package relay.use_case.signup;

import relay.entity.Instructor;
import relay.exceptions.ResourceAlreadyExistsException;

public interface SignupInstructorDataAccessInterface {
	void save(Instructor instructor) throws ResourceAlreadyExistsException;
}
