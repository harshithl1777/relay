package relay.data_access;

import relay.entity.Instructor;
import relay.exceptions.ResourceAlreadyExistsException;
import relay.exceptions.ResourceNotFoundException;
import relay.use_case.login.LoginInstructorDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryInstructorDataAccessObject implements LoginInstructorDataAccessInterface {

    private final Map<String, Instructor> instructors = new HashMap<>();

    @Override
    public Instructor readByEmailAddress(String emailAddress) throws ResourceNotFoundException {
        for (Instructor instructor : instructors.values()) {
            if (instructor.getEmailAddress().equals(emailAddress)) {
                return instructor;
            }
        }
        throw new ResourceNotFoundException("No such instructor exists");
    }

    public void save(Instructor instructor) throws ResourceAlreadyExistsException {
        instructors.put(instructor.getInstructorID(), instructor);
    }


}