package com.digdes.school.serialization;

import org.junit.After;

import java.io.File;
import java.util.Date;

public abstract class AbstractSerializationTest {
    protected String fileName;
    protected Marshaller marshaller;

    protected SimplePojo buildPojo() {
        SimplePojo pojo = new SimplePojo();
        pojo.setDate(new Date());
        pojo.setName("a name");
        pojo.setVal(1337);
        pojo.setSkipSaving("no serializable value");
        return pojo;
    }

    @After
    public void tearDown() {
        File file = new File(fileName);
        if (file.exists())
            //noinspection ResultOfMethodCallIgnored
            file.delete();
    }
}
