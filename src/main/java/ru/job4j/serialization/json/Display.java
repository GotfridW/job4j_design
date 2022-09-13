package ru.job4j.serialization.json;

public class Display {
    private final double size;
    private final String ratio;
    private final String resolution;


    public Display(double size, String ratio, String resolution) {
        this.size = size;
        this.ratio = ratio;
        this.resolution = resolution;
    }

    public double getSize() {
        return size;
    }

    public String getRatio() {
        return ratio;
    }

    public String getResolution() {
        return resolution;
    }

    @Override
    public String toString() {
        return "Display{"
                + "size=" + size
                + ", aspectRatio='" + ratio + '\''
                + ", resolution='" + resolution
                + '\'' + '}';
    }
}
