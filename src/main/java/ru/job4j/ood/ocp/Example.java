package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.List;

public class Example {

    private List<String> list = new ArrayList<>(List.of("Ivan", "Petr", "Vadim"));

    /* Нарушение OCP, т.к. метод transform работает с данными из глобальной переменной list,
    * при этом ничего не принимает на вход. Тем самым при расширении функционала приложения и
    * модификации list изменится и возвращаемое значение метода transform. */

    public String transform(StringBuilder stringBuilder) {
        return stringBuilder.append(list.get(0))
                .append(" ").append(list.get(1))
                .append(" ").append(list.get(2))
                .append(";").toString();
    }
}
