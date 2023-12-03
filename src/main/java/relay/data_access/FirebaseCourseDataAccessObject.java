
package relay.data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import relay.entity.Course;
import relay.entity.Instructor;
import relay.use_case.createcourse.CreateCourseDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FirebaseCourseDataAccessObject implements CreateCourseDataAccessInterface {
    private final Firestore db;

    public FirebaseCourseDataAccessObject() {
        this.db = FirestoreSingleton.get();
    }

    /**
     * Creates a new course document in Firestore and sets the courseID within the provided Course object.
     *
     * @param course The Course object containing course details to be added to Firestore.
     * @throws RuntimeException If an InterruptedException or ExecutionException occurs during Firestore operations.
     */
    public void createCourse(Course course) throws InterruptedException, ExecutionException {
        Map<String, Object> courseDocument = new HashMap<>();
        courseDocument.put("courseName", course.getCourseName());
        courseDocument.put("instructorID", course.getInstructor().getInstructorID());

        ApiFuture<DocumentReference> newCourseDocument = db.collection("courses").add(courseDocument);
        String courseID = newCourseDocument.get().getId();
        course.setCourseID(courseID);
    }

    /**
     * Retrieves a list of courses associated with a specific instructor from the Firestore database.
     *
     * @param instructor The Instructor object for which courses are to be retrieved.
     * @return An ArrayList of Course objects associated with the specified instructor.
     * @throws RuntimeException If an InterruptedException or ExecutionException occurs during Firestore operations.
     */
    public ArrayList<Course> getCoursesByInstructor(Instructor instructor) throws InterruptedException, ExecutionException {
        Query query = db.collection("courses")
                .whereEqualTo("instructorID", instructor.getInstructorID());

        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<Course> courses = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {

            Map<String, Object> courseData = document.getData();

            String courseID = document.getId();
            String courseName = (String) courseData.get("courseName");

            Course course = new Course(courseID, courseName, instructor);
            courses.add(course);

        }
        return courses;
    }

    /**
     * Checks if a course with the given courseID exists in the Firestore database.
     *
     * @param courseID The ID of the course to check for existence.
     * @return true if the course exists, false otherwise.
     * @throws RuntimeException If an ExecutionException or InterruptedException occurs during Firestore operations.
     */
    public boolean exists(String courseID) throws InterruptedException, ExecutionException {
        ApiFuture<DocumentSnapshot> retrievedcourseDocument = db.collection("courses").document(courseID).get();
        DocumentSnapshot courseDocument = retrievedcourseDocument.get();
        return courseDocument.exists();
    }

}

