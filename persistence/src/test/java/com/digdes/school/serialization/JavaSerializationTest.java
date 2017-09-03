package com.digdes.school.serialization;

import org.junit.Test;

import java.io.IOException;
import java.io.NotSerializableException;

public class JavaSerializationTest {
    private static final String FILE_NAME = "pojo.bin";

    @Test(expected = NotSerializableException.class)
    public void testForgotInterfaceDeclaration() throws IOException {
        class NoSerializable {
            String someStr;
        }
        NoSerializable ns = new NoSerializable();
        ns.someStr = "test";

        JavaSerialization.saveObject(ns, FILE_NAME);
    }

    @Test
    public void testSavingAndLoading() {

    }
}


class SimplePojo {
    String name;
}