package com.digdes.school.serialization;

import java.io.IOException;

/**
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public class JaxbSerialization implements Marshaller{
    @Override
    public void saveObject(Object obj, String fileName) throws IOException {

    }

    @Override
    public <T> T loadObject(String fileName, Class<T> klass) throws IOException, ClassNotFoundException {
        return null;
    }
}
