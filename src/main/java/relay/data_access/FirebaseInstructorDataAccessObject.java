package relay.data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import relay.exceptions.ResourceNotFoundException;
import relay.entity.Instructor;

import java.util.concurrent.ExecutionException;

/**
 * Provides data access methods for interacting with the Firebase Firestore database
 * to perform CRUD operations on Instructor entities.
 */
public class FirebaseInstructorDataAccessObject {

    /**
     * Saves the provided Instructor object to the Firestore database.
     *
     * @param instructor The Instructor object to be saved.
     */
    public void save(Instructor instructor) {
        if (exists(instructor.getInstructorID())) {
            update(instructor);
        } else {
            create(instructor);
        }
    }

    private void create(Instructor instructor) {
        ApiFuture<DocumentReference> docRef = FirestoreSingleton.get().collection("instructors").add(instructor);
        try {
            instructor.setInstructorID(docRef.get().getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void update(Instructor instructor) {
        FirestoreSingleton.get().collection("instructors").document(instructor.getInstructorID()).set(instructor);
    }

    /**
     * Reads an Instructor object from the Firestore database based on the provided instructorID.
     *
     * @param instructorID The unique identifier of the Instructor.
     * @return The retrieved Instructor object, or null if not found or an error occurs.
     */
    public Instructor read(String instructorID) {
        ApiFuture<DocumentSnapshot> future = FirestoreSingleton.get().collection("instructors").document(instructorID).get();

        try {
            DocumentSnapshot instructorDocument = future.get();
            if (instructorDocument.exists()) {
                Instructor instructor = instructorDocument.toObject(Instructor.class);
                assert instructor != null;
                instructor.setInstructorID(instructorID);
                return instructor;
            } else {
                return null;
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Checks if an Instructor object with the provided instructorID exists in the Firestore database.
     *
     * @param instructorID The unique identifier of the Instructor.
     * @return True if the Instructor exists, false otherwise.
     */
    public boolean exists(String instructorID) {
        if (instructorID == null || instructorID.isEmpty()) {
            return false;
        }
        ApiFuture<DocumentSnapshot> future = FirestoreSingleton.get().collection("instructors").document(instructorID).get();

        try {
            DocumentSnapshot instructorDocument = future.get();
            return instructorDocument.exists();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes an Instructor object from the Firestore database based on the provided instructorID.
     *
     * @param instructorID The unique identifier of the Instructor to be deleted.
     * @throws ResourceNotFoundException if the instructor does not exist in Firestore
     */
    public void delete(String instructorID) throws ResourceNotFoundException {
        if (!exists(instructorID)) {
            throw new ResourceNotFoundException("No such document exists.");
        }

        ApiFuture<WriteResult> deleteResult = FirestoreSingleton.get().collection("instructors").document(instructorID).delete();
        try {
            deleteResult.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
