package relay.data_access;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

/**
 * This class provides a threadsafe singleton instance of Firestore database for
 * interacting with Firebase Firestore.
 */
public class FirestoreSingleton {

	// Singleton instance of FirestoreSingleton
	private static volatile FirestoreSingleton singleton;

	// Reference to the Firestore database
	private final Firestore databaseReference;

	/**
	 * Private constructor to retrieve a single Firestore client instance.
	 * The private constructor is necessary to make it a singleton.
	 */
	private FirestoreSingleton() {
		databaseReference = FirestoreClient.getFirestore();
	}

	/**
	 * Returns the singleton's reference to FirestoreDatabase.
	 * This method is threadsafe.
	 *
	 * @return The Firestore database instance.
	 */
	public static Firestore get() {
		if (singleton == null) {
			synchronized (FirestoreSingleton.class) {
				// Recheck within the synchronized block
				if (singleton == null) {
					singleton = new FirestoreSingleton();
				}
			}
		}
		return singleton.databaseReference;
	}
}
