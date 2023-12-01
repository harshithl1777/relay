package relay.data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import relay.entity.Instructor;
import relay.entity.InstructorFactory;

import static org.junit.jupiter.api.Assertions.*;

public class FirebaseInstructorDataAccessObjectTest {
    FirebaseInstructorDataAccessObject dataAccessObject;


    @BeforeEach
    public void foo() {
        this.dataAccessObject = new FirebaseInstructorDataAccessObject();

    }


    @Test
    void testSaveAndReadInstructor() {

        // Create a sample Instructor for testing
        Instructor testInstructor = InstructorFactory.createInstructor("first", "last", "first.last@gmail.com");

        // Save the Instructor
        assertTrue(dataAccessObject.save(testInstructor));

        // Read the saved Instructor
        Instructor retrievedInstructor = dataAccessObject.read(testInstructor.getInstructorID());

        // Verify that the retrieved Instructor matches the saved Instructor
        assertNotNull(retrievedInstructor);
        assertEquals(testInstructor.getInstructorID(), retrievedInstructor.getInstructorID());
        assertEquals(testInstructor.getFirstName() + testInstructor.getLastName(), retrievedInstructor.getFirstName() + retrievedInstructor.getLastName());
        assertEquals(testInstructor.getEmailAddress(), retrievedInstructor.getEmailAddress());
    }

    @Test
    void testDeleteInstructor() {
        FirebaseInstructorDataAccessObject dao = new FirebaseInstructorDataAccessObject();

        // Create a sample Instructor for testing
        Instructor testInstructor = InstructorFactory.createInstructor("first", "last", "first.last@gmail.com");

        // Save the Instructor
        assertTrue(dao.save(testInstructor));

        // Delete the saved Instructor
        assertTrue(dao.delete(testInstructor.getInstructorID()));

        // Verify that the Instructor no longer exists in the database
        assertFalse(dao.exists(testInstructor.getInstructorID()));
    }

    @Test
    void testExists() {
        FirebaseInstructorDataAccessObject dao = new FirebaseInstructorDataAccessObject();

        // Create a sample Instructor for testing
        Instructor testInstructor = InstructorFactory.createInstructor("first", "last", "first.last@gmail.com");

        // Save the Instructor
        assertTrue(dao.save(testInstructor));

        // Verify that the Instructor exists in the database
        assertTrue(dao.exists(testInstructor.getInstructorID()));
    }

    @Test
    void testReadNonExistentInstructor() {
        FirebaseInstructorDataAccessObject dao = new FirebaseInstructorDataAccessObject();

        // Attempt to read an Instructor with a non-existent ID
        Instructor retrievedInstructor = dao.read("nonexistentID");

        // Verify that the retrieved Instructor is null
        assertNull(retrievedInstructor);
    }

    @Test
    void testDeleteNonExistentInstructor() {
        FirebaseInstructorDataAccessObject dao = new FirebaseInstructorDataAccessObject();

        // Attempt to delete an Instructor with a non-existent ID
        assertFalse(dao.delete("nonexistentID"));
    }
}
