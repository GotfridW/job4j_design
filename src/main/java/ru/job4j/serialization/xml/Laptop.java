package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "laptop")
@XmlAccessorType(XmlAccessType.FIELD)

public class Laptop {
    @XmlAttribute
    private String brand;
    @XmlAttribute
    private int cores;
    @XmlAttribute
    private boolean wifi;
    private Display display;

    private String[] ports;

    public Laptop() {
    }

    public Laptop(String brand, int cores, boolean wifi, Display display, String[] ports) {
        this.brand = brand;
        this.cores = cores;
        this.wifi = wifi;
        this.display = display;
        this.ports = ports;
    }

    @Override
    public String toString() {
        return "Laptop{"
                + "brand='" + brand + '\''
                + ", cores=" + cores
                + ", wifi=" + wifi
                + ", display=" + display
                + ", ports=" + Arrays.toString(ports)
                + '}';
    }
}
