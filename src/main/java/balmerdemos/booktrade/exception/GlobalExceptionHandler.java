package balmerdemos.booktrade.exception;

import org.slf4j.Logger;  // SLF4J Logger interface for logging messages with different severity levels (info, warn, error, etc.)
import org.slf4j.LoggerFactory;  // SLF4J LoggerFactory to create a logger instance
import org.springframework.http.HttpStatus;  // HTTP status codes (e.g., 200 OK, 404 Not Found) used in responses
import org.springframework.http.ResponseEntity;  // Represents HTTP responses, including status, headers, and body
import org.springframework.web.bind.annotation.ControllerAdvice;  // Allows the definition of global exception handlers across controllers
import org.springframework.web.bind.annotation.ExceptionHandler;  // Used to handle specific exceptions thrown in controller methods
import org.springframework.web.servlet.NoHandlerFoundException;  // Thrown when no handler (controller method) is found for a request URL

import java.time.ZonedDateTime;  // Represents a date and time with timezone information
import java.time.LocalDateTime;  // Represents a date and time without timezone information
import java.util.HashMap;  // A map implementation that stores key-value pairs (used here for building error responses)
import java.util.Map;  // A collection of key-value pairs, used for structured data storage (used for error responses)
import java.util.NoSuchElementException;  // Thrown when attempting to access an element that isn't present (e.g., empty Optional)
import java.util.UUID;  // Generates universally unique identifiers (UUIDs) used to trace errors across requests


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Generate a unique trace ID for each request
    private String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    // Handle invalid ObjectId formats
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        String traceId = generateTraceId();  // Generate a unique trace ID for each request
        logger.warn("Invalid request (traceId: {}): {}", traceId, ex.getMessage());

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Bad Request");
        errorResponse.put("msg", "Invalid ObjectId");
        errorResponse.put("traceId", traceId);
        errorResponse.put("requestPath", "/api/mensajes/sender/{senderId}/receiver/{receiverId}");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        String traceId = generateTraceId();  // Generate a unique trace ID for each request
        logger.error("Unexpected error (traceId: {}): {}", traceId, ex.getMessage(), ex);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("error", "Internal Server Error");
        errorResponse.put("msg", "Unexpected error");
        errorResponse.put("traceId", traceId);
        errorResponse.put("requestPath", "/api/mensajes/sender/{senderId}/receiver/{receiverId}");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle No Message Found
    @ExceptionHandler(NoMessagesFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoMessagesFoundException(NoMessagesFoundException ex) {
        String traceId = generateTraceId();  // Generate a unique trace ID for each request
        logger.info("404 Not Found (traceId: {}): {}", traceId, ex.getMessage());

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error", "Not Found");
        errorResponse.put("msg", "No messages found");
        errorResponse.put("traceId", traceId);
        errorResponse.put("requestPath", "/api/mensajes/sender/{senderId}/receiver/{receiverId}");

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle 404 errors (No handler found for a path)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        String traceId = generateTraceId();  // Generate a unique trace ID for each request
        logger.info("404 Not Found (traceId: {}): {}", traceId, ex.getRequestURL());

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error", "Not Found");
        errorResponse.put("msg", "URL not found");
        errorResponse.put("traceId", traceId);
        errorResponse.put("requestPath", ex.getRequestURL());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", ZonedDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
