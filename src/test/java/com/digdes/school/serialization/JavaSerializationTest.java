package com.digdes.school.serialization;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Test for java serialization
 *
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public class JavaSerializationTest {
    private static final String FILE_NAME = "pojo.bin";

    Marshaller marshaller;

    @Before
    public void setUp() {
        marshaller = new JavaSerialization();
    }

    @After
    public void tearDown() {
        File file = new File(FILE_NAME);
        if (file.exists())
            //noinspection ResultOfMethodCallIgnored
            file.delete();
    }

    @Test(expected = NotSerializableException.class)
    public void testForgotInterfaceDeclaration() throws IOException {
        class NoSerializable {
            String someStr;
        }
        NoSerializable ns = new NoSerializable();
        ns.someStr = "test";

        marshaller.saveObject(ns, FILE_NAME);
    }

    @Test
    public void testSavingAndLoading() throws IOException, ClassNotFoundException {
        SimplePojo pojo = buildPojo();
        marshaller.saveObject(pojo, FILE_NAME);
        SimplePojo loaded =  marshaller.loadObject(FILE_NAME, SimplePojo.class);
        Assert.assertNotNull(loaded);
        Assert.assertNull(loaded.getSkipSaving());
        Assert.assertEquals(pojo, loaded);
    }

    private SimplePojo buildPojo() {
        SimplePojo pojo = new SimplePojo();
        pojo.setDate(new Date());
        pojo.setName("a name");
        pojo.setVal(1337);
        pojo.setSkipSaving("no serializable value");
        return pojo;
    }
}


class SimplePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int val;
    private Date date;

    private transient String skipSaving;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSkipSaving() {
        return skipSaving;
    }

    public void setSkipSaving(String skipSaving) {
        this.skipSaving = skipSaving;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplePojo that = (SimplePojo) o;
        return val == that.val &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getVal(), getDate());
    }
}