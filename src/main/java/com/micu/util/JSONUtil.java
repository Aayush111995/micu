package com.micu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Aayush Chaudhary
 */
public class JSONUtil {

    private static final Logger LOG = LogManager.getLogger(JSONUtil.class);

    private static ObjectMapper mapper = new ObjectMapper();

    private JSONUtil() {

    }

    public static <T> T getObjectFromJsonString(String json, Class<T> cls) {
        try {
            return mapper.readValue(json.getBytes(StandardCharsets.UTF_8), cls);
        } catch (IOException e) {
            LOG.error("Error in getting the Object from json string" + e, e);
        }
        return null;
    }

    public static <T> String getStringFromObject(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("Error in getting string from object " + e, e);
        }
        return null;
    }

    public static JsonNode getJsonNodeFromString(String json) {
        try {
            return mapper.reader().readTree(json);
        } catch (IOException e) {
            LOG.error("Error in getting json node from json " + e, e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, Object> getMapFromObject(T object) {
        return mapper.convertValue(object, Map.class);
    }
}
