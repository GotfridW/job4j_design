package ru.job4j.ood.srp.formatter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarJsonAdapter implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public JsonElement serialize(Calendar src, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(DATE_FORMAT.format(src.getTime()));
    }


    @Override
    public Calendar deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(DATE_FORMAT.parse(json.getAsJsonPrimitive().getAsString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}
