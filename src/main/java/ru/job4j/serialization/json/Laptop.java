package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Laptop {
    private final String brand;
    private final int cores;
    private final boolean wifi;
    private final Display display;
    private final String[] ports;

    public Laptop(String brand, int cores, boolean wifi,
                  Display display, String[] ports) {
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

    public static void main(String[] args) {
        var laptop = new Laptop("Asus", 2, true,
                new Display(15.6, "16:9", "1366:768"),
                new String[]{"USB, HDMI, CardReader"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(laptop));
        final String laptopJson =
                "{"
                        + "\"brand\":\"Asus\","
                        + "\"cores\":2,"
                        + "\"wifi\":true,"
                        + "\"display\":"
                        + "{"
                        + "\"size\":15.6,"
                        + "\"ratio\":\"16:9\","
                        + "\"resolution\":\"1366:768\""
                        + "},"
                        + "\"ports\":"
                        + "[\"USB\",\"HDMI\",\"CardReader\"]"
                        + "}";
        final Laptop laptopMod = gson.fromJson(laptopJson, Laptop.class);
        System.out.println(laptopMod);
    }
}
