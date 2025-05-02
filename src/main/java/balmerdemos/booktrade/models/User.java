package balmerdemos.booktrade.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;  // Importing annotation for custom deserialization of JSON
import com.fasterxml.jackson.databind.annotation.JsonSerialize;  // Importing annotation for custom serialization of JSON
import balmerdemos.booktrade.serializer.ObjectIdSerializer;  // Importing custom serializer for ObjectId
import balmerdemos.booktrade.serializer.ObjectIdDeserializer;  // Importing custom deserializer for ObjectId

import org.bson.types.ObjectId;  // Importing ObjectId, which is used to represent MongoDB's unique identifiers
import org.springframework.data.annotation.Id;  // Importing the @Id annotation, used to mark the primary key of a document in MongoDB
import org.springframework.data.mongodb.core.mapping.Document;  // Importing the @Document annotation, indicating this class represents a MongoDB document

import java.util.List;  // Importing List interface for storing collections of data

// Annotating the class as a MongoDB document, mapped to the "users" collection
@Document(collection = "users")
public class User {

    // The primary key for the document, will be automatically mapped by MongoDB
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)  // Custom serialization of ObjectId to handle its representation in JSON
    @JsonDeserialize(using = ObjectIdDeserializer.class)  // Custom deserialization of ObjectId from JSON
    private ObjectId id;

    private String username;  // User's username
    private String email;  // User's email address

    // A list of ObjectId representing the user's favorite items (could be books, authors, etc.)
    @JsonSerialize(contentUsing = ObjectIdSerializer.class)  // Custom serialization for the list items (ObjectId)
    @JsonDeserialize(contentUsing = ObjectIdDeserializer.class)  // Custom deserialization for the list items (ObjectId)
    private List<ObjectId> favoritos;

    // A list of ObjectId representing the user's books
    @JsonSerialize(contentUsing = ObjectIdSerializer.class)  // Custom serialization for the list items (ObjectId)
    @JsonDeserialize(contentUsing = ObjectIdDeserializer.class)  // Custom deserialization for the list items (ObjectId)
    private List<ObjectId> libros;

    // Getter for the id field (ObjectId)
    public ObjectId getId() { return id; }

    // Setter for the id field (ObjectId)
    public void setId(ObjectId id) { this.id = id; }

    // Getter for the username field (String)
    public String getUsername() { return username; }

    // Setter for the username field (String)
    public void setUsername(String username) { this.username = username; }

    // Getter for the email field (String)
    public String getEmail() { return email; }

    // Setter for the email field (String)
    public void setEmail(String email) { this.email = email; }

    // Getter for the favoritos field (List<ObjectId>)
    public List<ObjectId> getFavoritos() { return favoritos; }

    // Setter for the favoritos field (List<ObjectId>)
    public void setFavoritos(List<ObjectId> favoritos) { this.favoritos = favoritos; }

    // Getter for the libros field (List<ObjectId>)
    public List<ObjectId> getLibros() { return libros; }

    // Setter for the libros field (List<ObjectId>)
    public void setLibros(List<ObjectId> libros) { this.libros = libros; }
}
