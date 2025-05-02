package balmerdemos.booktrade.dto;

// Import for MongoDB's ObjectId, used as unique identifier for documents
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Data Transfer Object (DTO) for the User entity.
 * DTOs are used to safely transfer data between layers or over the network
 * without exposing the full internal model or database structure.
 */
public class UserDTO {

    // Unique identifier for the user (from MongoDB)
    private ObjectId id;

    // Username field
    private String username;

    // Email field
    private String email;

    // List of favorite items, represented by their ObjectId references
    private List<ObjectId> favoritos;

    // List of books owned or associated with the user, also by ObjectId
    private List<ObjectId> libros;

    // Getter for id
    public ObjectId getId() { return id; }

    // Setter for id
    public void setId(ObjectId id) { this.id = id; }

    // Getter for username
    public String getUsername() { return username; }

    // Setter for username
    public void setUsername(String username) { this.username = username; }

    // Getter for email
    public String getEmail() { return email; }

    // Setter for email
    public void setEmail(String email) { this.email = email; }

    // Getter for favoritos (favorites)
    public List<ObjectId> getFavoritos() { return favoritos; }

    // Setter for favoritos
    public void setFavoritos(List<ObjectId> favoritos) { this.favoritos = favoritos; }

    // Getter for libros (books)
    public List<ObjectId> getLibros() { return libros; }

    // Setter for libros
    public void setLibros(List<ObjectId> libros) { this.libros = libros; }
}
