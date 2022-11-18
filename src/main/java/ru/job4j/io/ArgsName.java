package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("No element with that key exist.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .filter(this::contract)
                .map(s -> s.split("=", 2))
                .forEach(s -> values.put(s[0].substring(1), s[1]));
    }

    public boolean contract(String s) {
        if ((s.indexOf("=") == s.lastIndexOf("=")) && s.endsWith("=")) {
            throw new IllegalArgumentException("String can't end with equality"
                    + " sign while it has only one equality sign.");
        }
        if (s.startsWith("-=")) {
            throw new IllegalArgumentException("No key detected.");
        }
        if (s.indexOf("=") == s.length()) {
            throw new IllegalArgumentException("No value detected.");
        }
        if (!s.contains("-")) {
            throw new IllegalArgumentException("No dash detected.");
        }
        if (!s.contains("=")) {
            throw new IllegalArgumentException("No equality sign detected.");
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Array of strings must be not empty.");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
