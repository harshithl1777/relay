package relay.unit;

import relay.entity.Instructor;
import relay.exceptions.ResourceAlreadyExistsException;
import relay.exceptions.ResourceNotFoundException;
import relay.use_case.login.LoginInstructorDataAccessInterface;
import relay.use_case.show_courses.ShowCourseInstructorDataAccessInterface;
import relay.use_case.start_session.StartSessionInstructorDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory Instructor Data Access Object for testing purposes.
 * This class provides methods for reading and saving instructor data in memory.
 */
public class InMemoryInstructorDataAccessObject implements LoginInstructorDataAccessInterface, StartSessionInstructorDataAccessInterface, ShowCourseInstructorDataAccessInterface {

    // Storage for instructor data in memory
    private final Map<String, Instructor> instructors = new HashMap<>();
    private static int nextID = 0;

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
        if (instructor.getInstructorID() != null) {
            instructors.put(instructor.getInstructorID(), instructor);
        } else {
            setNextID();
            instructor.setInstructorID(String.valueOf(nextID));
            instructors.put(String.valueOf(nextID), instructor);
        }

    }


    private void setNextID() {
        while (instructors.containsKey(String.valueOf(nextID))) {
            nextID++;
        }

    }

    public boolean exists(String instructorID) {
        return instructors.containsKey(instructorID);
    }

}
