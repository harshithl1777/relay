package relay.unit.data_access;

import com.google.cloud.firestore.Firestore;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import relay.data_access.FirebaseInitializationSingleton;
import relay.data_access.FirestoreSingleton;

import static org.junit.jupiter.api.Assertions.*;

public class FirestoreDatabaseTest {
	@BeforeAll
	public static void initializeFirebase() {
		FirebaseInitializationSingleton.initialize();
	}

	/**
	 * Test to verify that the {@link FirestoreSingleton#get()} method returns a
	 * non-null Firestore reference.
	 */
	@Test
	public void testGetReturnsNonNullFirestoreReference() {
		Firestore firestore = FirestoreSingleton.get();
		assertNotNull(firestore);
	}

	/**
	 * Test to check that consecutive calls to {@link FirestoreSingleton#get()}
	 * return the same Firestore reference, ensuring it is a singleton.
	 */
	@Test
	public void testReturnsSingularFirestoreReference() {
		Firestore firestore1 = FirestoreSingleton.get();
		Firestore firestore2 = FirestoreSingleton.get();

		assertNotNull(firestore1);
		assertNotNull(firestore2);
		assertSame(firestore1, firestore2);

	}

}
