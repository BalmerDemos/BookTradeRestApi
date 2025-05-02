package balmerdemos.booktrade.repository;

import balmerdemos.booktrade.models.User;  // Importing the User model class
import org.bson.types.ObjectId;  // Importing ObjectId class, which represents MongoDB's unique identifiers
import org.springframework.data.mongodb.repository.MongoRepository;  // Importing the Spring Data MongoDB repository interface

import org.springframework.data.domain.Page;  // Importing the Page class, used for pagination
import org.springframework.data.domain.Pageable;  // Importing the Pageable class, used to provide pagination information

import java.util.Optional;  // Importing Optional, which is used to represent an optional value (may or may not be present)

public interface UserRepository extends MongoRepository<User, ObjectId> {
    // Custom method to find a user by their username (returns an Optional object)
    Optional<User> findByUsername(String username);

    // Custom method to find a user by their email (returns an Optional object)
    Optional<User> findByEmail(String email);

    // Custom method to delete a user by their ObjectId (ID)
    void deleteById(ObjectId id);

    // Custom method to delete a user by their username
    void deleteByUsername(String username);

    // Custom method to delete a user by their email
    void deleteByEmail(String email);

    // Custom method to find all users with pagination support
    Page<User> findAll(Pageable pageable);  // This allows fetching all users in pages for efficient data loading
}
