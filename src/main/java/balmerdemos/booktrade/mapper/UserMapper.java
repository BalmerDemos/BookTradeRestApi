package balmerdemos.booktrade.mapper;

import balmerdemos.booktrade.models.User;  // Importing the User model class
import balmerdemos.booktrade.dto.UserDTO;  // Importing the UserDTO data transfer object class
import org.bson.types.ObjectId;  // Importing ObjectId, which is used to represent MongoDB object identifiers

import java.util.List;  // Importing List, a collection interface for storing user data
import java.util.stream.Collectors;  // Importing stream API for transforming collections (e.g., mapping ObjectIds to Strings)

// A utility class for converting between User entity and UserDTO
public class UserMapper {

    // Converts a User entity to a UserDTO
    public static UserDTO toDTO(User user) {
        if (user == null) return null;  // If the user is null, return null DTO

        // Create a new DTO instance and map User fields to the DTO fields
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());  // Set the User's ObjectId
        dto.setUsername(user.getUsername());  // Set the User's username
        dto.setEmail(user.getEmail());  // Set the User's email
        dto.setFavoritos(user.getFavoritos());  // Set the User's favorite list
        dto.setLibros(user.getLibros());  // Set the User's books list
        return dto;  // Return the populated UserDTO
    }

    // Converts a UserDTO to a User entity
    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;  // If the DTO is null, return null User entity

        // Create a new User instance and map DTO fields to the User entity fields
        User user = new User();
        user.setId(dto.getId());  // Set the User's ObjectId
        user.setUsername(dto.getUsername());  // Set the User's username
        user.setEmail(dto.getEmail());  // Set the User's email
        user.setFavoritos(dto.getFavoritos());  // Set the User's favorite list
        user.setLibros(dto.getLibros());  // Set the User's books list
        return user;  // Return the populated User entity
    }

    // Helper method to convert a list of ObjectIds to a list of Strings (e.g., for displaying purposes)
    private static List<String> objectIdListToString(List<ObjectId> ids) {
        return ids != null ? ids.stream().map(ObjectId::toHexString).collect(Collectors.toList()) : null;  // Convert each ObjectId to a string representation
    }

    // Helper method to convert a list of Strings back to a list of ObjectIds (e.g., for database operations)
    private static List<ObjectId> stringListToObjectId(List<String> ids) {
        return ids != null ? ids.stream().map(ObjectId::new).collect(Collectors.toList()) : null;  // Convert each string back to an ObjectId
    }
}
