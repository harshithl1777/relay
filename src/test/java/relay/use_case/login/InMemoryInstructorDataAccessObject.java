package relay.use_case.login;

import relay.entity.Instructor;
import relay.exceptions.ResourceAlreadyExistsException;
import relay.exceptions.ResourceNotFoundException;
import relay.use_case.login.LoginInstructorDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory Instructor Data Access Object for testing purposes.
 * This class provides methods for reading and saving instructor data in memory.
 */
public class InMemoryInstructorDataAccessObject implements LoginInstructorDataAccessInterface {

    // Storage for instructor data in memory
    private final Map<String, Instructor> instructors = new HashMap<>();

    /**
     * Retrieves an instructor by their email address.
     *
     * @param emailAddress The email address of the instructor to retrieve.
     * @return The instructor associated with the given email address.
     * @throws ResourceNotFoundException if no instructor is found with the provided email address.
     */
    @Override
    public Instructor readByEmailAddress(String emailAddress) throws ResourceNotFoundException {
        for (Instructor instructor : instructors.values()) {
            if (instructor.getEmailAddress().equals(emailAddress)) {
                return instructor;
            }
        }
        throw new ResourceNotFoundException("No such instructor exists");
    }

    /**
     * Saves an instructor to the in-memory data storage.
     *
     * @param instructor The instructor to be saved.
     * @throws ResourceAlreadyExistsException if an instructor with the same ID already exists.
     */
    public void save(Instructor instructor) throws ResourceAlreadyExistsException {
        instructors.put(instructor.getInstructorID(), instructor);
    }
}
