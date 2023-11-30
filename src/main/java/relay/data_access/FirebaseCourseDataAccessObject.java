
package relay.data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;

import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import relay.entity.Course;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FirebaseCourseDataAccessObject {
    private static String FIREBASE_DATABASE_URL = "https:blitz-web1.firebaseio.com/";
    private static String FIREBASE_CREDENTIALS_FILE = "src/main/resources/firebase.json";


    private final Firestore db;

    public FirebaseCourseDataAccessObject() {
//        configureFirebase();
        db = FirestoreClient.getFirestore();

    }


//    private void configureFirebase() {
//        try {
//            FileInputStream FirebaseCredentials = new FileInputStream(FIREBASE_CREDENTIALS_FILE);
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(FirebaseCredentials))
//                    .setDatabaseUrl(FIREBASE_DATABASE_URL)
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }



    public void create(Course course) {
        Map<String, Object> courseDocument = new HashMap<>();
        courseDocument.put("courseID", course.getCourseID());
        courseDocument.put("courseName", course.getCourseName());
        courseDocument.put("Instructor", course.getInstructor());

        ApiFuture<WriteResult> future = db.collection("courses").document(course.getCourseName()).set(courseDocument);
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (ExecutionException e) {
            System.out.println(e);
        }


    }

}