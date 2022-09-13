package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.List;

public class ToJsonConverter {
    public static void main(String[] args) {
        var jsonDisplay = new JSONObject(
                "{\"size\":15.6,\"ratio\":\"16:9\",\"resolution\":\"1366x768\"}");
        List<String> list = Arrays.asList("USB", "HDMI", "CardReader");
        var jsonPorts = new JSONArray(list);
        var laptop = new Laptop("Asus", 2, true,
                new Display(15.6, "16:9", "1366x768"),
                new String[]{"USB, HDMI, CardReader"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("brand", laptop.getBrand());
        jsonObject.put("cores", laptop.getCores());
        jsonObject.put("wifi", laptop.isWifi());
        jsonObject.put("display", jsonDisplay);
        jsonObject.put("ports", jsonPorts);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(laptop));
    }
}
