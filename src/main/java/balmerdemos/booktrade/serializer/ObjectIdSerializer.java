package balmerdemos.booktrade.serializer;

import com.fasterxml.jackson.core.JsonGenerator;  // Used to write JSON content
import com.fasterxml.jackson.databind.SerializerProvider;  // Provides context for the serializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer;  // Base class for custom serializers
import org.bson.types.ObjectId;  // Represents MongoDB's unique identifier type

import java.io.IOException;  // Handles input/output exceptions

// Custom serializer to convert MongoDB ObjectId to a JSON-friendly string format
public class ObjectIdSerializer extends StdSerializer<ObjectId> {

    // Constructor that tells Jackson this serializer handles ObjectId instances
    public ObjectIdSerializer() {
        super(ObjectId.class);  // Pass ObjectId class to parent StdSerializer
    }

    // The serialize method is called when converting ObjectId to JSON
    @Override
    public void serialize(ObjectId value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null) {
            // Convert ObjectId to its hexadecimal string representation and write it as a JSON string
            gen.writeString(value.toHexString());
        }
    }
}
