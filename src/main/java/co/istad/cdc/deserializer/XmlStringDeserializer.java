package co.istad.cdc.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class XmlStringDeserializer<T> extends JsonDeserializer<T> {

    private final XmlMapper xmlMapper;
    private final JavaType targetType;

    public XmlStringDeserializer(Class<T> targetClass) {
        this.xmlMapper = new XmlMapper();
        this.targetType = xmlMapper.getTypeFactory().constructType(targetClass);

        // Configure to handle lists properly
        this.xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.xmlMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    // New constructor for generic types
    public XmlStringDeserializer(JavaType targetType) {
        this.xmlMapper = new XmlMapper();
        this.targetType = targetType;

        // Configure to handle lists properly
        this.xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.xmlMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String xmlString = p.getText();

        if (xmlString == null || "__debezium_unavailable_value".equals(xmlString)) {
            return null;
        }

        try {
            return xmlMapper.readValue(xmlString, targetType);
        } catch (Exception e) {
            return null;
        }
    }
}
