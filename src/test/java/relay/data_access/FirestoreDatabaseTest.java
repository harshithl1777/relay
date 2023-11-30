package relay.data_access;

import org.junit.Test;
import com.google.cloud.firestore.Firestore;

import static org.junit.jupiter.api.Assertions.*;

public class FirestoreDatabaseTest {

    /**
     * Test to verify that the {@link FirestoreDatabase#get()} method returns a
     * non-null Firestore reference.
     */
    @Test
    public void testGetReturnsNonNullFirestoreReference() {
        Firestore firestore = FirestoreDatabase.get();
        assertNotNull(firestore);
    }

    /**
     * Test to check that consecutive calls to {@link FirestoreDatabase#get()}
     * return the same Firestore reference, ensuring it is a singleton.
     */
    @Test
    public void testReturnsSingularFirestoreReference() {
        Firestore firestore1 = FirestoreDatabase.get();
        Firestore firestore2 = FirestoreDatabase.get();

        assertNotNull(firestore1);
        assertNotNull(firestore2);
        assertSame(firestore1, firestore2);

    }

}