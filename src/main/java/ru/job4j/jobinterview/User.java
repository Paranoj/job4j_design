package ru.job4j.jobinterview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", age=" + age
                + '}';
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "Vasya", 55));
        list.add(new User(2, "Petya", 14));
        list.add(new User(3, "Jora", 19));
        list.add(new User(4, "Jora", 19));

        List<User> newList = list.stream().filter(user -> user.age >= 18)
                .sorted(Comparator.comparingInt(User::getAge).reversed())
                .distinct().toList();
        newList.forEach(System.out::println);
    }
}
