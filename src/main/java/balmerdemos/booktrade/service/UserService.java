package balmerdemos.booktrade.service;

// Importing the User model class
import balmerdemos.booktrade.models.User;

// Importing the repository interface for User-related database operations
import balmerdemos.booktrade.repository.UserRepository;

// MongoDB's ObjectId class used as the unique identifier
import org.bson.types.ObjectId;

// Spring annotation to mark this class as a service component
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Java utility classes for optional values and collections
import java.util.List;
import java.util.Optional;

// Spring Data support for pagination
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service  // Marks this class as a Spring service for dependency injection
public class UserService {

    @Autowired  // Automatically injects the UserRepository dependency
    private UserRepository userRepository;

    // Create a new user document in the database
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Retrieve a user by their username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Retrieve a user by their email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Retrieve a user by their ObjectId (as an ObjectId instance)
    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    // Retrieve all users with pagination support
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    // Retrieve a user by their ObjectId (as a string)
    public Optional<User> getUserById(String id) {
        return userRepository.findById(new ObjectId(id));
    }

    // Update an existing user document
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Delete a user by their ObjectId
    public void deleteUser(ObjectId id) {
        userRepository.deleteById(id);
    }

}
