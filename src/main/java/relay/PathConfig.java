package relay;

import java.io.IOException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class PathConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String[] pathPatterns = { "/", "/{x:^(?!api$).*$}/**" };
		registry
				// Capture everything (REST controllers get priority over this, see above)
				.addResourceHandler(pathPatterns)
				// Add locations where files might be found
				.addResourceLocations("classpath:/static/**")
				// Needed to allow use of `addResolver` below
				.resourceChain(true)
				// This thing is what does all the resolving. This impl. is responsible for
				// resolving ALL files. Meaning nothing gets resolves automatically by pointing
				// out "static" above.
				.addResolver(new PathResourceResolver() {
					@Override
					protected Resource getResource(String resourcePath, Resource location) throws IOException {
						Resource requestedResource = location.createRelative(resourcePath);

						// If we actually hit a file, serve that. This is stuff like .js and .css files.
						if (requestedResource.exists() && requestedResource.isReadable()) {
							return requestedResource;
						}

						// Anything else returns the index.
						return new ClassPathResource("/static/index.html");
					}
				});

	}
}
