package ru.job4j.solid.srp;

import java.util.List;

public interface User {
    /* Принцип SRP нарушается некорректным выделением абстракции,
    * методы userLogger и dbSave несут в себе отдельный функционал, который уместно выделить
    * в отдельные интерфейсы.*/

    void userLogger(List<User> users);

    void dbSave(List<User> users);

    List<User> save(User user);
}
