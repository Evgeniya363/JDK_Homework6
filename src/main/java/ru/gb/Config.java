package ru.gb;

import java.io.IOException;
import java.util.Properties;

public class Config {
    static int COUNT_DOORS;

    static {
        Properties props = new Properties();
        try {
            props.load(Config.class.getResourceAsStream("config"));
            COUNT_DOORS = Integer.valueOf(props.getProperty("countDoors", "3"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
