package co.istad.cdc.config;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.reflect.ReflectData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AvroConverterConfig {

    private static final ReflectData REFLECT_DATA = ReflectData.AllowNull.get();

    public static GenericRecord toGenericRecord(Object obj) {
        if (obj == null) {
            return null;
        }

        Schema schema = REFLECT_DATA.getSchema(obj.getClass());
        return toGenericRecord(obj, schema);
    }

    public static GenericRecord toGenericRecord(Object obj, Schema schema) {
        if (obj == null) {
            return null;
        }

        GenericRecord record = new GenericData.Record(schema);
        Class<?> clazz = obj.getClass();

        for (Schema.Field field : schema.getFields()) {
            try {
                Object value = getFieldValue(obj, field.name(), clazz);

                if (value != null) {
                    Schema fieldSchema = getActualSchema(field.schema());

                    // Handle different types
                    if (fieldSchema.getType() == Schema.Type.RECORD) {
                        // Nested object
                        value = toGenericRecord(value, fieldSchema);
                    } else if (fieldSchema.getType() == Schema.Type.ARRAY) {
                        // List of objects
                        value = convertList((List<?>) value, fieldSchema.getElementType());
                    }
                }

                record.put(field.name(), value);

            } catch (Exception e) {
                throw new RuntimeException("Error processing field: " + field.name(), e);
            }
        }

        return record;
    }

    private static Object getFieldValue(Object obj, String fieldName, Class<?> clazz) throws Exception {
        // Try direct field access
        Field field = findField(clazz, fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(obj);
        }
        return null;
    }

    private static Field findField(Class<?> clazz, String fieldName) {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Try all fields with case-insensitive comparison
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.getName().equalsIgnoreCase(fieldName)) {
                        return field;
                    }
                }
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

    private static Schema getActualSchema(Schema schema) {
        if (schema.getType() == Schema.Type.UNION) {
            // Return non-null type from union
            for (Schema s : schema.getTypes()) {
                if (s.getType() != Schema.Type.NULL) {
                    return s;
                }
            }
        }
        return schema;
    }

    private static List<Object> convertList(List<?> list, Schema elementSchema) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        List<Object> result = new ArrayList<>();

        if (elementSchema.getType() == Schema.Type.RECORD) {
            for (Object item : list) {
                result.add(toGenericRecord(item, elementSchema));
            }
        } else {
            result.addAll(list);
        }

        return result;
    }

    public static Schema getSchema(Class<?> clazz) {
        return REFLECT_DATA.getSchema(clazz);
    }
}

