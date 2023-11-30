package relay.data_access;

import org.junit.Test;
import com.google.cloud.firestore.Firestore;

import static org.junit.jupiter.api.Assertions.*;

public class FirestoreDatabaseTest {

    @Test
    public void testGetReturnsNonNullFirestoreReference() {
        Firestore firestore = FirestoreDatabase.get();
        assertNotNull(firestore);
    }

    @Test
    public void testReturnsSingularFirestoreReference() {
        Firestore firestore1 = FirestoreDatabase.get();
        Firestore firestore2 = FirestoreDatabase.get();

        assertNotNull(firestore1);
        assertNotNull(firestore2);
        assertSame(firestore1, firestore2);


    }

}