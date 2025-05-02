package balmerdemos.booktrade;

// Entry point class for launching the Spring Boot application
import org.springframework.boot.SpringApplication;

// Enables auto-configuration, component scanning, and configuration class declaration
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class that bootstraps the Spring Boot application.
 * The @SpringBootApplication annotation includes:
 *   - @Configuration: to allow registering beans
 *   - @EnableAutoConfiguration: to auto-configure Spring components based on classpath
 *   - @ComponentScan: to scan the current package and sub-packages for components (@RestController, @Service, etc.)
 */
@SpringBootApplication
public class BooktradeApplication {

	/**
	 * The main() method is the entry point of any Java application.
	 * SpringApplication.run() starts the entire Spring context, initializing all components.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BooktradeApplication.class, args);
	}

}
