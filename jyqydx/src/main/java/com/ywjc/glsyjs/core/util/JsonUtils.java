/*
 */
package com.ywjc.glsyjs.core.util;

import java.io.IOException;
import java.io.Writer;

import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

/**
 * Utils - JSON
 * 
 * @author
 * @version 1.0
 */
public final class JsonUtils {
	private static Logger log = Logger.getLogger(JsonUtils.class);  
	/** ObjectMapper */
	private static ObjectMapper mapper = new ObjectMapper();

	private JsonUtils() {
	}

	/**
	 * Object to JSON
	 * 
	 * @param value
	 * @return 
	 */
	public static String toJson(Object value) {
		
			try {
				return mapper.writeValueAsString(value);
			} catch (JsonProcessingException e) {
				log.error(e.getMessage());
				log.info(e);
			}
		
		return null;
	}

	/**
	 * JSON to Object
	 * 
	 * @param json
	 * @param valueType
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> valueType) {
		Assert.hasText(json);
		Assert.notNull(valueType);
			try {
				return mapper.readValue(json, valueType);
			} catch (JsonParseException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (JsonMappingException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IOException e) {
				log.error(e.getMessage());
				log.info(e);
			}
		
		return null;
	}

	/**
	 * JSON to Object
	 * 
	 * @param json
	 * @param typeReference
	 * @return
	 */
	public static <T> T toObject(String json, TypeReference<?> typeReference) {
		Assert.hasText(json);
		Assert.notNull(typeReference);
		
			try {
				return mapper.readValue(json, typeReference);
			} catch (JsonParseException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (JsonMappingException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IOException e) {
				log.error(e.getMessage());
				log.info(e);
			}
		
		return null;
	}

	/**
	 * JSON to Object
	 * 
	 * @param json
	 * @param javaType
	 * @return 
	 */
	public static <T> T toObject(String json, JavaType javaType) {
		Assert.hasText(json);
		Assert.notNull(javaType);
		
			try {
				return mapper.readValue(json, javaType);
			} catch (JsonParseException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (JsonMappingException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IOException e) {
				log.error(e.getMessage());
				log.info(e);
			}
		
		return null;
	}

	/**
	 * Write Object to stream
	 * 
	 * @param writer
	 * @param value
	 */
	public static void writeValue(Writer writer, Object value) {
		try {
			mapper.writeValue(writer, value);
		} catch (JsonGenerationException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (IOException e) {
			log.error(e.getMessage());
			log.info(e);
		}
	}

}