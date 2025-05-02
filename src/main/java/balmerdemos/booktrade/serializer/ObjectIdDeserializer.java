package balmerdemos.booktrade.serializer;

import com.fasterxml.jackson.core.JsonParser;  // Importing the JsonParser class from Jackson for parsing JSON data
import com.fasterxml.jackson.databind.DeserializationContext;  // Importing the DeserializationContext, which provides context for the deserialization process
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;  // Importing the StdDeserializer class from Jackson, which is a standard deserializer class
import org.bson.types.ObjectId;  // Importing the ObjectId class, which represents MongoDB's unique identifier

import java.io.IOException;  // Importing IOException to handle input-output exceptions during deserialization

// Custom deserializer for converting JSON ObjectId strings into MongoDB ObjectId
public class ObjectIdDeserializer extends StdDeserializer<ObjectId> {

    // Constructor that specifies the target class type for deserialization (ObjectId)
    public ObjectIdDeserializer() {
        super(ObjectId.class);  // Call the superclass constructor with ObjectId class type
    }

    // Overriding the deserialize method to convert JSON string to ObjectId
    @Override
    public ObjectId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String idStr = p.getText();  // Extract the string value of the JSON field
        // If the string is valid and can be converted to an ObjectId, create a new ObjectId instance
        return idStr != null && ObjectId.isValid(idStr) ? new ObjectId(idStr) : null;
    }
}
