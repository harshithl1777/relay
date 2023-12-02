/**
 * Unit tests for the {@link FirebaseInstructorDataAccessObject} class.
 * These tests cover the basic functionality of saving, reading, deleting, and checking the existence of instructors.
 */
package relay.data_access;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import relay.entity.Instructor;
import relay.entity.InstructorFactory;
import relay.exceptions.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link FirebaseInstructorDataAccessObject}.
 */
public class FirebaseInstructorDataAccessObjectTest {
    FirebaseInstructorDataAccessObject dataAccessObject;

    @BeforeAll
    public static void initializeFirebase() {
        FirebaseInitializationSingleton.initialize();
    }

    /**
     * Set up the test environment by initializing a new instance of {@link FirebaseInstructorDataAccessObject}.
     */
    @BeforeEach
    public void createInstructorDataAccessObject() {
        this.dataAccessObject = new FirebaseInstructorDataAccessObject();
    }

    /**
     * Test the saving and reading functionality of the {@link FirebaseInstructorDataAccessObject}.
     */
    @Test
    void testSaveAndReadInstructor() {
        // Create a sample Instructor for testing
        Instructor testInstructor = InstructorFactory.createInstructor("first", "last", "first.last@gmail.com");

        // Save the Instructor
        dataAccessObject.save(testInstructor);

        // Read the saved Instructor
        Instructor retrievedInstructor = dataAccessObject.read(testInstructor.getInstructorID());

        // Verify that the retrieved Instructor matches the saved Instructor
        assertNotNull(retrievedInstructor);
        assertEquals(testInstructor.getInstructorID(), retrievedInstructor.getInstructorID());
        assertEquals(testInstructor.getFirstName() + testInstructor.getLastName(), retrievedInstructor.getFirstName() + retrievedInstructor.getLastName());
        assertEquals(testInstructor.getEmailAddress(), retrievedInstructor.getEmailAddress());

        // delete the saved instructor for cleanup
        dataAccessObject.delete(testInstructor.getInstructorID());

    }

    /**
     * Test the deleting functionality of the {@link FirebaseInstructorDataAccessObject}.
     */
    @Test
    void testDeleteInstructor() {
        // Create a sample Instructor for testing
        Instructor testInstructor = InstructorFactory.createInstructor("first", "last", "first.last@gmail.com");

        // Save the Instructor
        dataAccessObject.save(testInstructor);

        // Delete the saved Instructor
        dataAccessObject.delete(testInstructor.getInstructorID());

        // Verify that the Instructor no longer exists in the database
        assertFalse(dataAccessObject.exists(testInstructor.getInstructorID()));
    }

    /**
     * Test the existence checking functionality of the {@link FirebaseInstructorDataAccessObject}.
     */
    @Test
    void testExists() {
        // Create a sample Instructor for testing
        Instructor testInstructor = InstructorFactory.createInstructor("first", "last", "first.last@gmail.com");

        // Save the Instructor
        dataAccessObject.save(testInstructor);

        // Verify that the Instructor exists in the database
        assertTrue(dataAccessObject.exists(testInstructor.getInstructorID()));

        // Delete the instructor for cleanup
        dataAccessObject.delete(testInstructor.getInstructorID());
    }

    /**
     * Test reading a non-existent instructor from the {@link FirebaseInstructorDataAccessObject}.
     */
    @Test
    void testReadNonExistentInstructor() {
        // Attempt to read an Instructor with a non-existent ID
        Instructor retrievedInstructor = dataAccessObject.read("nonexistentID");

        // Verify that the retrieved Instructor is null
        assertNull(retrievedInstructor);
    }

    /**
     * Test deleting a non-existent instructor from the {@link FirebaseInstructorDataAccessObject}.
     */
    @Test
    void testDeleteNonExistentInstructor() {
        // Attempt to delete an Instructor with a non-existent ID
        assertThrows(ResourceNotFoundException.class, () -> dataAccessObject.delete("nonexistentID"));
    }
}
