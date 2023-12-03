package relay.use_case.login;

import relay.entity.Instructor;
import relay.exceptions.ResourceNotFoundException;

public interface LoginInstructorDataAccessInterface {
    boolean exists(String instructorID);

    Instructor readByEmailAddress(String instructorID) throws ResourceNotFoundException;


}
