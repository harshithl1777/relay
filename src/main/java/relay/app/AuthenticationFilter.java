package relay.app;

import java.io.IOException;
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
		if (environment.getProperty("relay.environment").equals("PRODUCTION") && !requestURL.contains("health")) {
			try {
				String[] authorizationHeader = request.getHeader("Authorization").split("Bearer");
				if (authorizationHeader.length < 2)
					response.sendError(400);
				String socialToken = authorizationHeader[1];
				System.out.println(socialToken);
				FirebaseAuth.getInstance().verifyIdToken(socialToken);
			} catch (FirebaseAuthException e) {
				response.sendError(401, "Invalid social token.");
			}
		} else {
			chain.doFilter(servletRequest, servletResponse);
		}
	}

}
