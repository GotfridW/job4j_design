package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlToPojoConverter {
    public static void main(String[] args) throws Exception {
        final var laptop = new Laptop("Asus", 2, true,
                new Display(15.6, "16:9", "1366x768"),
                new String[]{"USB, HDMI, CardReader"});
        var context = JAXBContext.newInstance(Laptop.class);
        var marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (var writer = new StringWriter()) {
            marshaller.marshal(laptop, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        var unmarshaller = context.createUnmarshaller();
        try (var reader = new StringReader(xml)) {
            var result = (Laptop) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
