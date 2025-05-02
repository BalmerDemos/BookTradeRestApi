package balmerdemos.booktrade.config;

// Marks this class as a source of Spring bean definitions (used for configuration)
import org.springframework.context.annotation.Bean;

// Indicates that this class provides Spring configuration (like defining beans)
import org.springframework.context.annotation.Configuration;

// Provides convenient defaults for commonly used Spring Security configurations
import org.springframework.security.config.Customizer;

// Enables building the HTTP security filter chain using a fluent API
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

// Core Spring Security component that applies security rules to incoming HTTP requests
import org.springframework.security.web.SecurityFilterChain;

// Represents a CORS (Cross-Origin Resource Sharing) policy, e.g., allowed origins and headers
import org.springframework.web.cors.CorsConfiguration;

// Interface to provide CORS configurations for different paths/endpoints
import org.springframework.web.cors.CorsConfigurationSource;

// A CORS configuration source that maps URLs (e.g., "/api/**") to specific CORS settings
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// Utility class for creating lists of allowed methods, headers, etc. (e.g., Arrays.asList("GET", "POST"))
import java.util.Arrays;


/**
 * 🔐 Spring Security configuration class.
 * This defines how different HTTP endpoints are secured in the REST API.
 */
@Configuration
public class SecurityConfig {

    /**
     * ✅ Defines the security filter chain.
     * This method configures authentication, authorization, and CORS settings.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 🔓 Disable CSRF (Cross-Site Request Forgery) protection
                // Safe to disable for stateless REST APIs (especially when using JWT or HTTP Basic)
                .csrf(csrf -> csrf.disable())

                // 🌍 Enable CORS (Cross-Origin Resource Sharing)
                // Allows frontend apps hosted on other domains (e.g., localhost:3000) to access the API
                .cors(cors -> {}) // We can further customize CORS in a separate bean

                // 🔐 Authorization rules for different endpoints
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints (no login required)
                        .requestMatchers("/api/public/**").permitAll()

                        // User endpoints require authentication (e.g., login required)
                        .requestMatchers("/api/users/**").authenticated()

                        // Any other request also requires authentication by default
                        .anyRequest().authenticated()
                )

                // 🔑 Use HTTP Basic Authentication
                // WARNING: Not secure for production unless used over HTTPS
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * 🌍 Optional: Define a global CORS configuration.
     * Uncomment and customize if you want to explicitly define allowed origins, methods, etc.
     */
    /*
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow requests from this origin (e.g., your React frontend running on localhost:3000)
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));

        // Allow common HTTP methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Allow headers like Authorization
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    */
}
