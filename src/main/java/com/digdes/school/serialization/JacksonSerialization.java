package com.digdes.school.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public class JacksonSerialization implements Marshaller{

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void saveObject(Object obj, String fileName) throws IOException {
        mapper.writeValue(new File(fileName), obj);
    }

    @Override
    public <T> T loadObject(String fileName, Class<T> klass) throws IOException, ClassNotFoundException {
        return (T) mapper.readValue(new File(fileName), klass);
    }
}
