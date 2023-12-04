/**
 * Unit tests for the {@link FirebaseInstructorDataAccessObject} class.
 * These tests cover the basic functionality of saving, reading, deleting, and checking the existence of instructors.
 */
package relay.unit.data_access;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import relay.data_access.FirebaseInitializationSingleton;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.entity.Instructor;
import relay.exceptions.ResourceAlreadyExistsException;
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
	 * Set up the test environment by initializing a new instance of
	 * {@link FirebaseInstructorDataAccessObject}.
	 */
	@BeforeEach
	public void createInstructorDataAccessObject() {
		this.dataAccessObject = new FirebaseInstructorDataAccessObject();
	}

	/**
	 * Test the saving and reading functionality of the
	 * {@link FirebaseInstructorDataAccessObject}.
	 */
	@Test
	void testSaveAndReadInstructor() throws ResourceNotFoundException, ResourceAlreadyExistsException {
		// Create a sample Instructor for testing
		Instructor testInstructor = new Instructor("first", "last", "first.last@gmail.com");

		// Save the Instructor
		dataAccessObject.save(testInstructor);

		// Read the saved Instructor
		Instructor retrievedInstructor = dataAccessObject.read(testInstructor.getInstructorID());

		// Verify that the retrieved Instructor matches the saved Instructor
		assertNotNull(retrievedInstructor);
		assertEquals(testInstructor.getInstructorID(), retrievedInstructor.getInstructorID());
		assertEquals(testInstructor.getFirstName() + testInstructor.getLastName(),
				retrievedInstructor.getFirstName() + retrievedInstructor.getLastName());
		assertEquals(testInstructor.getEmailAddress(), retrievedInstructor.getEmailAddress());

		// delete the saved instructor for cleanup
		dataAccessObject.delete(testInstructor.getInstructorID());

	}

	/**
	 * Test that save called on an already saved instructor does not modify
	 * Instructor::instructorID.
	 */
	@Test
	void testSavingTwiceDoesNotModifyInstructorID() throws ResourceNotFoundException, ResourceAlreadyExistsException {
		// Create a sample Instructor for testing
		Instructor testInstructor = new Instructor("first", "last", "first.last@gmail.com");

		dataAccessObject.save(testInstructor);
		String previousID = testInstructor.getInstructorID();
		dataAccessObject.save(testInstructor);
		assert testInstructor.getInstructorID().equals(previousID);

		// delete the saved instructor for cleanup
		dataAccessObject.delete(testInstructor.getInstructorID());

	}

	/**
	 * Test the deleting functionality of the
	 * {@link FirebaseInstructorDataAccessObject}.
	 */
	@Test
	void testDeleteInstructor() throws ResourceNotFoundException, ResourceAlreadyExistsException {
		// Create a sample Instructor for testing
		Instructor testInstructor = new Instructor("first", "last", "first.last@gmail.com");

		// Save the Instructor
		dataAccessObject.save(testInstructor);

		// Delete the saved Instructor
		dataAccessObject.delete(testInstructor.getInstructorID());

		// Verify that the Instructor no longer exists in the database
		assertFalse(dataAccessObject.exists(testInstructor.getInstructorID()));
	}

	/**
	 * Test whether exists method of {@link FirebaseInstructorDataAccessObject}
	 * correctly returns true.
	 */
	@Test
	void testExistsReturnsTrue() throws ResourceNotFoundException, ResourceAlreadyExistsException {
		// Create a sample Instructor for testing
		Instructor testInstructor = new Instructor("first", "last", "first.last@gmail.com");

		// Save the Instructor
		dataAccessObject.save(testInstructor);

		// Verify that the Instructor exists in the database
		assertTrue(dataAccessObject.exists(testInstructor.getInstructorID()));

		// Delete the instructor for cleanup
		dataAccessObject.delete(testInstructor.getInstructorID());
	}

	/**
	 * Test whether exists method of {@link FirebaseInstructorDataAccessObject}
	 * correctly
	 * returns false with null instructorID.
	 */
	@Test
	void testExistsReturnsFalseWithNullInstructorID() {
		assertFalse(dataAccessObject.exists(null));
	}

	/**
	 * Test whether exists method of {@link FirebaseInstructorDataAccessObject}
	 * correctly
	 * returns false with empty instructorID.
	 */
	@Test
	void testExistsReturnsFalseWithEmptyInstructorID() {
		assertFalse(dataAccessObject.exists(""));
	}

	/**
	 * Test whether exists method of {@link FirebaseInstructorDataAccessObject}
	 * correctly
	 * returns false with blank instructorID.
	 */
	@Test
	void testExistsReturnsFalseWithBlankInstructorID() {
		assertFalse(dataAccessObject.exists("      "));
	}

	/**
	 * Test reading a non-existent instructor from the
	 * {@link FirebaseInstructorDataAccessObject}.
	 */
	@Test
	void testReadNonExistentInstructor() {
		// Attempt to read an Instructor with a non-existent ID
		assertThrows(ResourceNotFoundException.class, () -> dataAccessObject.read("nonexistentID"));
	}

	/**
	 * Test deleting a non-existent instructor from the
	 * {@link FirebaseInstructorDataAccessObject}.
	 */
	@Test
	void testDeleteNonExistentInstructor() {
		// Attempt to delete an Instructor with a non-existent ID
		assertThrows(ResourceNotFoundException.class, () -> dataAccessObject.delete("nonexistentID"));
	}


}

