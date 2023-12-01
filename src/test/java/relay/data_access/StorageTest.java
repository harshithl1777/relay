package relay.data_access;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class StorageTest {
    @BeforeClass
    public static void configureStorage() {
        try {
            String FIREBASE_CREDENTIALS_FILE = "src/main/resources/firebase.json";
            FileInputStream firebaseCredentials = new FileInputStream(FIREBASE_CREDENTIALS_FILE);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(firebaseCredentials))
                    .setStorageBucket("blitz-web1.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test to verify that the {@link StorageSingleton#get()} method returns a
     * non-null Bucket reference.
     */

    @Test
    public void testGetReturnsNonNullStorageReference() {

        Bucket bucket = StorageSingleton.get();
        assertNotNull(bucket);
    }

    /**
     * Test to check that consecutive calls to {@link StorageSingleton#get()}
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
