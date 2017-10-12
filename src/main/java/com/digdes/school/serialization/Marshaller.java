package com.digdes.school.serialization;

import java.io.IOException;

/**
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public interface Marshaller {
    void saveObject(Object obj, String fileName) throws IOException;

    <T> T loadObject(String fileName, Class<T> klass) throws IOException, ClassNotFoundException;
}
