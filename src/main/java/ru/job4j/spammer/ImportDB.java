package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties cfg;
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public boolean contract(String[] s) {
        if (s.length != 3) {
            throw new IllegalArgumentException("The string must consist of 2 elements: name and email.");
        }
        if (s[0].isEmpty() || s[1].isEmpty()) {
            throw new IllegalArgumentException("Name or email in the string is empty.");
        }
        return true;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .map(s -> s.trim().split(";", 3))
                    .filter(this::contract)
                    .forEach(strings -> users.add(new User(strings[0], strings[1])));
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("spammer.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
