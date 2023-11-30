package relay;

import java.io.FileInputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

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

	public static void main(String[] args) {
		configureFirebase();
		SpringApplication.run(RelayApplication.class, args);
	}
}
