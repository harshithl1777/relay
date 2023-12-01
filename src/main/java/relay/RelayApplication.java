package relay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import relay.data_access.FirebaseInitializationSingleton;

@RestController
@SpringBootApplication
public class RelayApplication {

	@RequestMapping("/health")
	String health() {
		return "200 OK";
	}

	public static void main(String[] args) {
		FirebaseInitializationSingleton.initialize();
		FirebaseInitializationSingleton.initialize();
		SpringApplication.run(RelayApplication.class, args);
	}
}
