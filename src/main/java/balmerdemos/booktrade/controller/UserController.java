// Package declaration for the controller class
package balmerdemos.booktrade.controller;

// DTO: Data Transfer Object, used to expose only safe user data to clients
import balmerdemos.booktrade.dto.UserDTO;

// Mapper to convert between Entity and DTO
import balmerdemos.booktrade.mapper.UserMapper;

// User model representing the MongoDB document
import balmerdemos.booktrade.models.User;

// Service layer for business logic
import balmerdemos.booktrade.service.UserService;

// MongoDB ObjectId for identifying users
import org.bson.types.ObjectId;

// Injects dependencies automatically
import org.springframework.beans.factory.annotation.Autowired;

// Spring Data's abstraction for pagination
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

// Used to wrap HTTP responses with appropriate status codes
import org.springframework.http.ResponseEntity;

// Annotations for mapping web requests to controller methods
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST Controller for managing User-related endpoints.
 * All endpoints in this controller are under /api/users
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    // Injects the user service automatically
    @Autowired
    private UserService userService;

    /**
     * Endpoint to create a new user.
     * Expects a JSON payload matching the UserDTO structure.
     */
    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        User createdUser = userService.createUser(user);
        return UserMapper.toDTO(createdUser);
    }

    /**
     * Get a user by their username.
     * Uses a ResponseEntity to return appropriate HTTP status.
     */
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        if (username == null || username.trim().isEmpty()) {
            return (ResponseEntity<UserDTO>) ResponseEntity.badRequest();
        }

        Optional<User> userOpt = userService.getUserByUsername(username);
        return userOpt.map(user -> ResponseEntity.ok(UserMapper.toDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get a user by their email.
     * Throws a runtime exception if the user is not found.
     */
    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) {
        Optional<User> userOpt = userService.getUserByEmail(email);
        return userOpt.map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Get a paginated list of all users.
     * Page and size can be passed as query parameters.
     */
    @GetMapping("/users")
    public Page<UserDTO> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getAllUsers(pageable).map(UserMapper::toDTO);
    }

    /**
     * Update an entire user entity by ID.
     * If user is not found, return 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User updatedUser = UserMapper.toEntity(userDTO);
        updatedUser.setId(new ObjectId(id)); // Ensure the ID is set for update
        User savedUser = userService.updateUser(updatedUser);
        return ResponseEntity.ok(UserMapper.toDTO(savedUser));
    }

    /**
     * Partial update to a user's email field.
     * Uses PATCH HTTP method.
     */
    @PatchMapping("/{id}/email")
    public ResponseEntity<UserDTO> updateUserEmail(
            @PathVariable String id,
            @RequestBody Map<String, String> updates) {

        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        if (updates.containsKey("email")) {
            user.setEmail(updates.get("email"));
        }

        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(UserMapper.toDTO(updatedUser));
    }

    /**
     * Deletes a user by ID.
     * Returns 204 No Content if deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(new ObjectId(id));
        return ResponseEntity.noContent().build();
    }

}
