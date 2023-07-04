package ru.job4j.ood.srp.model;

import lombok.*;
import ru.job4j.ood.srp.formatter.CalendarXmlAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Calendar;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {
    @XmlAttribute
    @EqualsAndHashCode.Include private String name;
    @XmlJavaTypeAdapter(CalendarXmlAdapter.class)
    private Calendar hired;
    @XmlJavaTypeAdapter(CalendarXmlAdapter.class)
    private Calendar fired;
    @XmlAttribute
    private double salary;
}


