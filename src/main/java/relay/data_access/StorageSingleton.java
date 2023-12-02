package relay.data_access;

import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;

/**
 * This class provides a threadsafe singleton instance of Bucket for Cloud Storage for interacting with Firebase Storage.
 */

public class StorageSingleton {

    // Singleton instance of StorageSingleton
    private static volatile StorageSingleton singleton;

    // Reference to the Storage bucket
    private final Bucket storageReference;

    /**
     * Private constructor to initialize the Storage bucket.
     * The private constructor is necessary to make it a singleton.
     */

    private StorageSingleton() {
        storageReference = StorageClient.getInstance().bucket();
    }

    /**
     * Returns the singleton's reference to StorageBucket.
     * This method is threadsafe.
     *
     * @return The Storage bucket instance.
     */
    public static Bucket get() {
        if (singleton == null) {
            synchronized (StorageSingleton.class) {
                if (singleton == null) {
                    singleton = new StorageSingleton();
                }
            }
        }
        return singleton.storageReference;
    }
}