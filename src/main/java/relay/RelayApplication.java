package relay;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import relay.data_access.FirebaseCourseDataAccessObject;
import relay.entity.Course;
import relay.entity.Instructor;

@RestController
@SpringBootApplication
public class RelayApplication {

	private static String FIREBASE_CREDENTIALS_FILE = "src/main/resources/firebase.json";

	@RequestMapping("/health")
	String health() {
		return "200 OK";
	}

	private static void configureFirebase() {
		try {
			FileInputStream FirebaseCredentials = new FileInputStream(FIREBASE_CREDENTIALS_FILE);
			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(FirebaseCredentials))
					.build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		configureFirebase();
		FirebaseCourseDataAccessObject dao = new FirebaseCourseDataAccessObject();
		Instructor instructor = new Instructor("Joe", "Smith", "joe@hotmail.com");
		instructor.setInstructorID("fwkf3");
		ArrayList<Course> courses = dao.getCoursesByInstructor(instructor);

		System.out.println(courses.get(1).getCourseName());

//		SpringApplication.run(RelayApplication.class, args);

	}
}
