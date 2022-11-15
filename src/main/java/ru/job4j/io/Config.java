package ru.job4j.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(this::contract)
                    .map(s -> s.trim().split("=", 2))
                    .forEach(s -> values.put(s[0], s[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean contract(String s) {
        if (s.startsWith("#") || (s.isBlank())) {
            return false;
        }
        if (s.startsWith("=") || ((s.indexOf("=") == s.lastIndexOf("=")) && s.endsWith("="))
                || !s.contains("=")) {
            throw new IllegalArgumentException(s);
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /*public static void main(String[] args) {
        Config config = new Config("app.properties");
        System.out.println(config);
        config.load();
        System.out.println(config.value("hibernate.connection.url"));
    }*/
}
