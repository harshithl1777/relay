package relay.data_access;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;

/**
 * This class provides a singleton instance of Firestore database for interacting with Firebase Firestore.
 */
public class FirestoreDatabase {

    // Path to the Firebase credentials file
    private String FIREBASE_CREDENTIALS_FILE = "src/main/resources/firebase.json";

    // Singleton instance of FirestoreDatabase
    private static FirestoreDatabase singleton;

    // Reference to the Firestore database
    private final Firestore databaseReference;

    /**
     * Private constructor to initialize the Firestore database.
     * The private constructor is necessary to make it a singleton.
     */
    private FirestoreDatabase() {
        configureFirebase();
        databaseReference = FirestoreClient.getFirestore();
    }

    /**
     * Configures the Firebase connection using the credentials from the specified file.
     */
    private void configureFirebase() {
        try {
            FileInputStream firebaseCredentials = new FileInputStream(FIREBASE_CREDENTIALS_FILE);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(firebaseCredentials))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Returns the singleton's reference to FirestoreDatabase.
     * This method is threadsafe.
     *
     * @return The Firestore database instance.
     */
    public static Firestore get() {
        if (singleton == null) {
            synchronized (FirestoreDatabase.class) {
                // Recheck within the synchronized block
                if (singleton == null) {
                    singleton = new FirestoreDatabase();
                }
            }
        }
        return singleton.databaseReference;
    }
}
