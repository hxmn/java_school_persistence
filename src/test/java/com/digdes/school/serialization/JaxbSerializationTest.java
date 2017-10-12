package com.digdes.school.serialization;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Test for java serialization
 *
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public class JaxbSerializationTest extends AbstractSerializationTest {
    @Before
    public void setUp() {
        fileName = "pojo.xml";
        marshaller = new JaxbSerialization();
    }

    @Test
    public void testSavingAndLoading() throws IOException, ClassNotFoundException {
        SimplePojo pojo = buildPojo();
        marshaller.saveObject(pojo, fileName);
        SimplePojo loaded = marshaller.loadObject(fileName, SimplePojo.class);

        String xml = IOUtils.toString(new FileInputStream(new File(fileName)));
        Assert.assertTrue(isXMLValid(xml));
        System.out.println(xml);
        Assert.assertNotNull(loaded);
        Assert.assertNull(loaded.getSkipSaving());
        Assert.assertEquals(pojo, loaded);
    }

    private boolean isXMLValid(String xml) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.parse(new ByteArrayInputStream(xml.getBytes("utf8")));
            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}