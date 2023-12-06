package relay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import relay.data_access.FirebaseInitializationSingleton;

@SpringBootApplication
@RestController
public class RelayApplication {

	@GetMapping("/api/health")
	ResponseEntity<HttpStatus> health() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public static void main(String[] args) {
		FirebaseInitializationSingleton.initialize();
		SpringApplication.run(RelayApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String[] allowedOrigins = { "https://localhost:3000", "https://relay.onrender.com" };
				registry.addMapping("/**").allowedOrigins(allowedOrigins);
			}
		};
	}
}
