package com.digdes.school.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public class JaxbSerialization implements Marshaller {
    @Override
    public void saveObject(Object obj, String fileName) throws IOException {
        FileWriter writer = new FileWriter(new File(fileName));
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(obj.getClass());
            javax.xml.bind.Marshaller m = context.createMarshaller();
            m.marshal(obj, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T loadObject(String fileName, Class<T> klass) throws IOException, ClassNotFoundException {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(klass);
            Unmarshaller m = context.createUnmarshaller();
            //noinspection unchecked
            return (T) m.unmarshal(new FileReader(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
