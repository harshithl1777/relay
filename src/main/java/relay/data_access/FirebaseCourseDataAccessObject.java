
package relay.data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import relay.entity.Course;
import relay.entity.Instructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FirebaseCourseDataAccessObject {


    /**
     * Creates a new course document in Firestore based on the given Course object.
     *
     * @param course The Course object containing course details to be added to Firestore.
     */
    public void createCourse(Course course) {
        Map<String, Object> courseDocument = new HashMap<>();
        courseDocument.put("courseID", course.getCourseID());
        courseDocument.put("courseName", course.getCourseName());
        courseDocument.put("instructorID", course.getInstructor().getInstructorID());

        ApiFuture<WriteResult> future = FirestoreSingleton.get().collection("courses").document(course.getCourseID()).set(courseDocument);
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }

    }

    /**
     * Retrieves a list of courses associated with a specific instructor from the Firestore database.
     *
     * @param instructor The Instructor object for which courses are to be retrieved.
     * @return An ArrayList of Course objects associated with the specified instructor.
     * @throws ExecutionException   If the execution of the query fails.
     * @throws InterruptedException If the query execution is interrupted.
     */
    public ArrayList<Course> getCoursesByInstructor(Instructor instructor) {
        Firestore db = FirestoreSingleton.get();
        Query query = db.collection("courses")
                .whereEqualTo("instructorID", instructor.getInstructorID());

        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        ArrayList<Course> courses = new ArrayList<>();

        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {

                Map<String, Object> courseData = document.getData();

                String courseID = (String) courseData.get("courseID");
                String courseName = (String) courseData.get("courseName");

                Course course = new Course(courseID, courseName, instructor);
                courses.add(course);

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return courses;
    }

    /**
     * Checks if a course with the given courseID exists in the Firestore database.
     *
     * @param courseID The ID of the course to check for existence.
     * @return true if the course exists, false otherwise.
     */
    public boolean exists(String courseID) {
        ApiFuture<DocumentSnapshot> retrievedcourseDocument = FirestoreSingleton.get().collection("courses").document(courseID).get();

        try {
            DocumentSnapshot courseDocument = retrievedcourseDocument.get();
            return courseDocument.exists();

        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e);
            return false;
        }
    }

}

