package relay.data_access;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * This class runs threadsafe Firebase initialization code in order to
 * interact with the Firebase clients.
 */
public class FirebaseInitializationSingleton {

	// Singleton instance of FirebaseInitializationSingleton
	private static volatile FirebaseInitializationSingleton singleton;

	// Firebase secrets file location
	private static String FIREBASE_CREDENTIALS_FILE = "src/main/resources/firebase.json";

	/**
	 * Private constructor to initialize the Firebase configuration app.
	 * The private constructor is necessary to make it a singleton.
	 */
	private FirebaseInitializationSingleton() {
		try {
			FileInputStream FirebaseCredentials = new FileInputStream(FIREBASE_CREDENTIALS_FILE);
			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(FirebaseCredentials))
					.setStorageBucket("relay-dev0.appspot.com")
					.build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Initializes firebase if it not already done in the current program.
	 * This method is threadsafe.
	 */
	public static void initialize() {
		if (singleton == null) {
			synchronized (FirebaseInitializationSingleton.class) {
				// Recheck within the synchronized block
				if (singleton == null) {
					singleton = new FirebaseInitializationSingleton();
				}
			}
		}
	}
}
