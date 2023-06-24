package com.ua.rosella.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bson.types.ObjectId;

import java.io.IOException;

public class ObjectIdSerializer extends JsonSerializer<ObjectId> {
    @Override
    public void serialize(ObjectId objectId, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("hexId", objectId.toString());
        jgen.writeNumberField("timestamp", objectId.getTimestamp());
        jgen.writeNumberField("date", objectId.getDate().getTime());
        jgen.writeEndObject();
    }
}
