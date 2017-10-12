package com.digdes.school.serialization;

import java.io.IOException;

/**
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public interface Marshaller {
    void saveObject(Object obj, String fileName) throws IOException;
    Object loadObject(String fileName) throws IOException, ClassNotFoundException;
}
