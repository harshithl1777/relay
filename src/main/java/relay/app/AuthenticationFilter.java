package relay.app;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {
	@Autowired
	private Environment environment;

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String requestURL = request.getRequestURL().toString();
		String[] protectedMethods = { "GET", "POST", "PUT", "DELETE", "PATCH", "HEAD" };
		String[] protectedPaths = { "sessions", "instructors", "courses" };
		String requestMethod = request.getMethod();

		if (environment.getProperty("relay.environment").equals("PRODUCTION") && !requestURL.contains("health")
				&& Arrays.stream(protectedMethods).anyMatch(v -> v.contains(requestMethod)
						&& Arrays.stream(protectedPaths).anyMatch(p -> requestURL.contains(p)))) {
			try {
				String authorizationHeader = request.getHeader("Authorization");
				if (authorizationHeader == null) {
					response.sendError(400);
				} else if (authorizationHeader.split(" ").length < 2) {
					response.sendError(400);
				} else {
					String socialToken = authorizationHeader.split(" ")[1];
					FirebaseAuth.getInstance().verifyIdToken(socialToken);
					chain.doFilter(servletRequest, servletResponse);
				}
			} catch (FirebaseAuthException e) {
				e.printStackTrace();
				response.sendError(401, "Invalid social token.");
			}
		} else {
			chain.doFilter(servletRequest, servletResponse);
		}
	}

}
