package com.ngv.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private static Gson gson = new Gson();

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Convert object to json
     *
     * @param object Object
     * @return json
     */
    public static String toJson(Object object) {
        String jsonString = "";
        try {
            jsonString = getMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("Can't build json from object");
        }
        return jsonString;
    }

    /**
     * Gets the json by binding to the specified object.
     *
     * @param valueType Type to bind json (any class)
     * @param json      String json
     * @param <T>       Type to bind json
     * @return Object
     */
    public static <T> T fromJson(String json, Class<T> valueType) {
        if (json == null) {
            return null;
        }
        T object = null;
        try {
            object = getMapper().readValue(json, valueType);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return object;
    }

    public static String toJsonDateAsTimestamps(final Object object) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonString = "Can't build json from object";
        }
        return jsonString;
    }

    public static Class<?> typeToClass(final Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        }
        if (type instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType) type;
            return (Class<?>) parameterizedType.getRawType();
        }
        return null;
    }

    public static String toJsonFromObject(Object object) {
        if (object == null) {
            return null;
        }
        String res = null;
        try {
            res = gson.toJson(object);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return res;
    }

    public static <T> T toObject(String json, Type type) {
        if (json == null) {
            return null;
        }
        T object = null;
        try {
            object = gson.fromJson(json, type);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return object;
    }

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.mappedFeature(), false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        return mapper;
    }

}
