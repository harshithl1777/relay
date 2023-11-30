package relay.data_access;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;

public class FirestoreDatabase {
    private String FIREBASE_CREDENTIALS_FILE = "src/main/resources/firebase.json";

    private static FirestoreDatabase singleton;
    private Firestore databaseReference;

    private FirestoreDatabase() {
        configureFirebase();
        databaseReference = FirestoreClient.getFirestore();
    }

    private void configureFirebase() {
        try {
            FileInputStream FirebaseCredentials = new FileInputStream(FIREBASE_CREDENTIALS_FILE);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(FirebaseCredentials))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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