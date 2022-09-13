package ru.job4j.serialization.xml;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "display")
public class Display {
    @XmlAttribute
    private double size;
    @XmlAttribute
    private String ratio;
    @XmlAttribute
    private String resolution;

    public Display() {
    }

    public Display(double size, String ratio, String resolution) {
        this.size = size;
        this.ratio = ratio;
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "Display{"
                + "size=" + size
                + ", ratio='" + ratio + '\''
                + ", resolution='" + resolution + '\''
                + '}';
    }
}
