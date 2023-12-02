package relay.data_access;

import com.google.cloud.storage.Bucket;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FirebaseStorageTest {
	@BeforeAll
	public static void initializeFirebase() {
		FirebaseInitializationSingleton.initialize();
	}

	/**
	 * Test to verify that the StorageSingleton.get() method returns a
	 * non-null Bucket reference.
	 */
	@Test
	public void testGetReturnsNonNullStorageReference() {
		Bucket bucket = StorageSingleton.get();
		assertNotNull(bucket);
	}

	/**
	 * Test to check that consecutive calls to StorageSingleton.get()
	 * return the same Bucket reference, ensuring it is a singleton.
	 */
	@Test
	public void testReturnsSingularStorageReference() {
		Bucket bucket1 = StorageSingleton.get();
		Bucket bucket2 = StorageSingleton.get();

		assertNotNull(bucket1);
		assertNotNull(bucket2);
		assertSame(bucket1, bucket2);
	}
}
