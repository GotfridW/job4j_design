package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.TimeZone;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "John Locke";
        int age = 35;
        float heightInFeet = 6.8f;
        double weight = 70.3;
        LOG.debug("User info name : {}, age : {}, height : {}, weight : {}",
                name, age, heightInFeet, weight);
        boolean active = true;
        short id = 42;
        LOG.info("Profile info: active : {}, id : {}", active, id);
        byte status = 2;
        LOG.warn("Warning: current status : {}", status);
        char code = 'F';
        long time = System.currentTimeMillis();
        LOG.error("Error occurred: code: {}, at: {}", code, time);
    }
}
