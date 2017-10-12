package com.digdes.school.serialization;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Test for jackson serialization
 *
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public class JacksonSerializationTest extends AbstractSerializationTest {
    @Before
    public void setUp() {
        fileName = "pojo.json";
        marshaller = new JacksonSerialization();
    }

    @Test
    public void testSavingAndLoading() throws IOException, ClassNotFoundException {
        SimplePojo pojo = buildPojo();
        marshaller.saveObject(pojo, fileName);
        SimplePojo loaded = marshaller.loadObject(fileName, SimplePojo.class);

        System.out.println("Resulted json:");
        System.out.println(IOUtils.toString(new FileInputStream(new File(fileName))));

        Assert.assertNotNull(loaded);
        Assert.assertNull(loaded.getSkipSaving());
        Assert.assertEquals(pojo, loaded);
    }
}