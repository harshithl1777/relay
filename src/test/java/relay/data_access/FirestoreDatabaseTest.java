package relay.data_access;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;

import java.io.FileInputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FirestoreDatabaseTest {
    @BeforeAll
    public static void configureFirebase() {
        try {
            String FIREBASE_CREDENTIALS_FILE = "src/main/resources/firebase.json";
            FileInputStream firebaseCredentials = new FileInputStream(FIREBASE_CREDENTIALS_FILE);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(firebaseCredentials))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
