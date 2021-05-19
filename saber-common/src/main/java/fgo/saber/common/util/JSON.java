package fgo.saber.common.util;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JSON {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // config
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    }

    public static <T> String obj2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {
            log.warn("parse object to String exception, error:", e);
            return null;
        }
    }

    public static <T> T string2Obj(String src, Class<T> clazz) {
        if (src == null || clazz == null) {
            return null;
        }
        try {
            return objectMapper.readValue(src, clazz);
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, clazz, e);
            return null;
        }
    }

    public static <T> List<T> string2Arr(String src, Class<T> clazz) {
        if (src == null || clazz == null) {
            return null;
        }
        try {
            CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return objectMapper.readValue(src, type);
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, clazz, e);
            return null;
        }
    }

}
