package ru.job4j.softaria;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ChangesInWebsitesCodeTest {

    @Test
    void whenPageAdded() {
        ChangesInWebsitesCode changesInWebsitesCode = new ChangesInWebsitesCode();
        var y = List.of(new Website("https://career.habr.com/vacancies/java_developer?=page1",
                "12345"));
        var t = List.of(new Website("https://career.habr.com/vacancies/java_developer?=page1",
                "12345"),
                new Website("https://career.habr.com/vacancies/java_developer?=page2",
                "12345"));
        var yesterday = changesInWebsitesCode.mapFilling(y);
        var today = changesInWebsitesCode.mapFilling(t);
        changesInWebsitesCode.specification(yesterday, today);
        String actual = changesInWebsitesCode.appender(new StringBuilder());
        String expected = String.join(
                System.lineSeparator(),
                "Здравствуйте, дорогая и.о. секретаря",
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:",
                String.format("Появились следующие новые страницы: [%s]", t.get(1).getUrl()),
                "Изменились следующие страницы: []",
                "Исчезли следующие страницы: []",
                "С уважением, автоматизированная система мониторинга.");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenPageChanged() {
        ChangesInWebsitesCode changesInWebsitesCode = new ChangesInWebsitesCode();
        var y = List.of(new Website("https://career.habr.com/vacancies/java_developer?=page1",
                "12345"),
                new Website("https://career.habr.com/vacancies/java_developer?=page2",
                        "12345"));
        var t = List.of(new Website("https://career.habr.com/vacancies/java_developer?=page1",
                        "1234"),
                new Website("https://career.habr.com/vacancies/java_developer?=page2",
                        "123456"));
        var yesterday = changesInWebsitesCode.mapFilling(y);
        var today = changesInWebsitesCode.mapFilling(t);
        changesInWebsitesCode.specification(yesterday, today);
        String actual = changesInWebsitesCode.appender(new StringBuilder());
        String expected = String.join(
                System.lineSeparator(),
                "Здравствуйте, дорогая и.о. секретаря",
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:",
                "Появились следующие новые страницы: []",
                String.format("Изменились следующие страницы: [%s, %s]", t.get(1).getUrl(), t.get(0).getUrl()),
                "Исчезли следующие страницы: []",
                "С уважением, автоматизированная система мониторинга.");
        assertThat(actual).isEqualTo(expected);
    }

}