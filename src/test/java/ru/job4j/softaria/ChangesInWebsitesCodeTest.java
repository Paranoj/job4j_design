package ru.job4j.softaria;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ChangesInWebsitesCodeTest {

    @Test
    void whenPageAdded() {
        Changes changes = new ChangesWebsitesCode();
        var y = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1",
                "<12345>"));
        var t = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1",
                "<12345>", "https://career.habr.com/vacancies/java_developer?=page2", "<12345>"));
        String actual = changes.letter(y, t);
        String expected = String.join(
                System.lineSeparator(),
                "Здравствуйте, дорогая и.о. секретаря",
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:",
                "Появились следующие новые страницы: [https://career.habr.com/vacancies/java_developer?=page2]",
                "Изменились следующие страницы: []",
                "Исчезли следующие страницы: []",
                "С уважением, автоматизированная система мониторинга.");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenPagesChanged() {
        Changes changes = new ChangesWebsitesCode();
        var y = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1",
                "<12345>", "https://career.habr.com/vacancies/java_developer?=page2",
                "<12345>"));
        var t = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1",
                "<1234>", "https://career.habr.com/vacancies/java_developer?=page2", "<123456>"));
        String actual = changes.letter(y, t);
        String expected = String.join(
                System.lineSeparator(),
                "Здравствуйте, дорогая и.о. секретаря",
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:",
                "Появились следующие новые страницы: []",
                "Изменились следующие страницы: [https://career.habr.com/vacancies/java_developer?=page2, https://career.habr.com/vacancies/java_developer?=page1]",
                "Исчезли следующие страницы: []",
                "С уважением, автоматизированная система мониторинга.");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenPageLost() {
        Changes changes = new ChangesWebsitesCode();
        var y = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1",
                "<12345>", "https://career.habr.com/vacancies/java_developer?=page2", "<12345>"));
        var t = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1",
                "<12345>"));
        String actual = changes.letter(y, t);
        String expected = String.join(
                System.lineSeparator(),
                "Здравствуйте, дорогая и.о. секретаря",
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:",
                "Появились следующие новые страницы: []",
                "Изменились следующие страницы: []",
                "Исчезли следующие страницы: [https://career.habr.com/vacancies/java_developer?=page2]",
                "С уважением, автоматизированная система мониторинга.");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenMapIsEmptyException() {
        Changes changes = new ChangesWebsitesCode();
        var y = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1",
                "<12345>", "https://career.habr.com/vacancies/java_developer?=page2", "<12345>"));
        Map<String, String> t = new HashMap<>(Map.of());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> changes.letter(y, t))
                .withMessageMatching("Map is empty.");
    }

    @Test
    void whenInvalidUrlException() {
        Changes changes = new ChangesWebsitesCode();
        var y = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1",
                "<12345>", "https://career.habr.com/vacancies/java_developer?=page2", "<12345>"));
        var t = new HashMap<>(Map.of("career.habr.com/vacancies/java_developer?=page1",
                "<12345>"));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> changes.letter(y, t))
                .withMessageContaining("Map must consist of url as key: ");
    }

    @Test
    void whenInvalidHTMLException() {
        Changes changes = new ChangesWebsitesCode();
        var y = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1",
                "<12345>", "https://career.habr.com/vacancies/java_developer?=page2", "<12345>"));
        var t = new HashMap<>(Map.of("https://career.habr.com/vacancies/java_developer?=page1",
                "12345"));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> changes.letter(y, t))
                .withMessageContaining("Map must consist of html as value: ");
    }
}
