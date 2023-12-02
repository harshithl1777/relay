package data_access;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import relay.data_access.StorageSingleton;
import relay.entity.Session;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @BeforeAll
    public static void configureStorage() {
        try {
            String FIREBASE_CREDENTIALS_FILE = "src/main/resources/firebase.json";
            FileInputStream firebaseCredentials = new FileInputStream(FIREBASE_CREDENTIALS_FILE);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(firebaseCredentials))
                    .setStorageBucket("relay-dev0.appspot.com")
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
    @Test
    public void testCreateQrCode() throws IOException {
        Session session = new Session(null, "classID", null, null, "alphaNumericCode");
        session.generateQRCode();
        assert Session.checkIfImageExists("QRCodeNew.png");
        Session.deleteFile("QRCodeNew.png");
    }
    @Test
    public void testDeleteQrCodeImage() throws IOException {
        Bucket initialBucket = StorageSingleton.get();
        List<Blob> initialBlobs = getBlobsFromBucket(initialBucket);
        Session session = new Session(null, "classID", null, null, "alphaNumericCode");
        session.generateQRCode();
        Session.deleteFile("QRCodeNew.png");
        Bucket updatedBucket = StorageSingleton.get();
        List<Blob> updatedBlobs = getBlobsFromBucket(updatedBucket);

        assertBlobsEqual(initialBlobs, updatedBlobs);
    }


    private List<Blob> getBlobsFromBucket(Bucket bucket) {
        List<Blob> blobsHold = new ArrayList<>();
        Page<Blob> blobs = bucket.list();

        for (Blob blob : blobs.iterateAll()) {
           blobsHold.add(blob);
        }
        return blobsHold;
    }

    private void assertBlobsEqual(List<Blob> initialBlobs, List<Blob> updatedBlobs) {
        assertEquals(initialBlobs.size(), updatedBlobs.size());

        for (Blob initialBlob : initialBlobs) {
            assertTrue(blobExistsInList(initialBlob, updatedBlobs));
        }
    }

    private boolean blobExistsInList(Blob blob, List<Blob> blobList) {
        for (Blob b : blobList) {
            if (b.getName().equals(blob.getName())) {
                return true;
            }
        }
        return false;
    }
}
