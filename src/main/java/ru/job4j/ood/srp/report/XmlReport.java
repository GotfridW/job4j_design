package ru.job4j.ood.srp.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XmlReport implements Report {
    private final Store store;
    private final Marshaller marshaller;

    public XmlReport(Store store) {
        this.store = store;
        try {
            JAXBContext context = JAXBContext.newInstance(EmployeeWrapper.class);
            this.marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new EmployeeWrapper(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }

    @XmlRootElement(name = "employees")
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    private static class EmployeeWrapper {
        private List<Employee> employee;
    }
}
