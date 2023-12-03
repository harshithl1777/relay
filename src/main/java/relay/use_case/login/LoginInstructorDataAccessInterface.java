package relay.use_case.login;

import relay.entity.Instructor;
import relay.exceptions.ResourceNotFoundException;

public interface LoginInstructorDataAccessInterface {
    Instructor readByEmailAddress(String instructorID) throws ResourceNotFoundException;


}
