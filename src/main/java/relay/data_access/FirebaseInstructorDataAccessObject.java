package relay.data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;

import com.google.cloud.firestore.DocumentSnapshot;
import relay.entity.Instructor;
import relay.entity.InstructorFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;



public class FirebaseInstructorDataAccessObject {
    private final InstructorFactory instructorFactory;

    public FirebaseInstructorDataAccessObject(InstructorFactory instructorFactory) {
        this.instructorFactory = instructorFactory;
    }


    public boolean create(Instructor instructor) {
        ApiFuture<DocumentReference> addedDocRef = FirestoreSingleton.get().collection("instructors").add(instructor);
        try {
            instructor.setInstructorID(addedDocRef.get().getId());
            return true;
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
            return false;
        }
    }

    public Instructor read(String instructorID) {
        ApiFuture<DocumentSnapshot> future = FirestoreSingleton.get().collection("instructors").document(instructorID).get();

        try {
            DocumentSnapshot instructorDocument = future.get();
            if (instructorDocument.exists()) {
                return instructorDocument.toObject(Instructor.class);
            } else {
                return null;
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }


    }


    private Map<String, Object> prepareDocument(Instructor instructor) {
        Map<String, Object> instructorDocument = new HashMap<>();
        instructorDocument.put("firstName", instructor.getFirstName());
        instructorDocument.put("LastName", instructor.getLastName());
        instructorDocument.put("emailAddress", instructor.getEmailAddress());
        return instructorDocument;
    }


}
