package relay.use_case.login;

import relay.entity.Instructor;
import relay.exceptions.ResourceNotFoundException;

/**
 * Interface defining methods for accessing instructor login data.
 */
public interface LoginInstructorDataAccessInterface {

    /**
     * Retrieves an instructor by their email address.
     *
     * @param emailAddress The email address of the instructor to retrieve.
     * @return The instructor associated with the given email address.
     * @throws ResourceNotFoundException if no instructor is found with the provided email address.
     */
    Instructor readByEmailAddress(String emailAddress) throws ResourceNotFoundException;

}
