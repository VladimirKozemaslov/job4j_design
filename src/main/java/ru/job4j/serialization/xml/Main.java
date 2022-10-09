package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Conscript conscript = new Conscript(
                true,
                "Driver",
                837,
                new Person(
                        true,
                        32,
                        new Contact("11-111"),
                        "Worker", "Married"),
                new String[] {"Driving", "Swimming"}
        );
        JAXBContext context = JAXBContext.newInstance(Conscript.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(conscript, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Conscript result = (Conscript) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}