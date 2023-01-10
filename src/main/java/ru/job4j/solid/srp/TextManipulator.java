package ru.job4j.solid.srp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextManipulator {
    private String text;

    public TextManipulator(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String appendText(String str) {
        return text = text.concat(str);
    }

    public List<String> textSplitter(String str) {
        List<String> list = new ArrayList<>();
        var splitted = str.split("\\.");
        Collections.addAll(list, splitted);
        return list;
    }

    /* Некорректное выделение абстракции, метод printText может быть выделен в класс TextPrinter, в котором
    * впоследствии будут реализованы методы, несущие в себе функции вывода.*/
    public void printText() {
        System.out.println(new TextManipulator(text).getText());
    }
}
