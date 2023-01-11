package ru.job4j.softaria;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangesInWebsitesCode {

    private static final String LOST = "Исчезли следующие страницы: ";
    private static final String ADDED = "Появились следующие новые страницы: ";
    private static final String CHANGED = "Изменились следующие страницы: ";
    private static final String LS = System.lineSeparator();

    /**
     * Созданы хранилища для объектов Website с целью демонстрации работы приложения.
     */
    private static final List<Website> websitesY = List.of(
            new Website("https://career.habr.com/vacancies/java_developer?=page1",
                    "12345"),
            new Website("https://career.habr.com/vacancies/java_developer?=page2",
                    "12345"),
            new Website("https://career.habr.com/vacancies/java_developer?=page3",
                    "12345"),
            new Website("https://career.habr.com/vacancies/java_developer?=page5",
                    "123")
    );
    private static final List<Website> websitesT = List.of(
            new Website("https://career.habr.com/vacancies/java_developer?=page1",
                    "1234"),
            new Website("https://career.habr.com/vacancies/java_developer?=page4",
                    "12345"),
            new Website("https://career.habr.com/vacancies/java_developer?=page5",
                    "12345")
    );

    /**
     * Метод производит валидацию хранилища объектов Web по наличию значений
     * в нём. Проверяет что ключом хэш-таблицы является URL-ссылка.
     */
    public void validation(List<Website> web) {
        if (web.isEmpty()) {
            throw new IllegalArgumentException("List is empty.");
        }
        for (Website w : web) {
            try {
                new URL(w.getUrl());
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException(String.format(
                        "Map must consist of url as key, and html as value: %s",
                        w.getUrl()
                ));
            }
        }
    }

    /**
     * Метод принимает на вход список объектов типа Website и добавляет их в хэш-таблицы,
     * которые даны по условию.
     */
    public Map<String, String> mapFilling(List<Website> websitesList) {
        validation(websitesList);
        Map<String, String> map = new HashMap<>();
        for (Website websites : websitesList) {
            map.put(websites.getUrl(), websites.getHtml());
        }
        return map;
    }


    /**
     * Метод принимает на вход 2 хэш-таблицы, содержащие состояния сайтов на вчера и сегодня соответственно.
     * Производится добавление URL-ов в соответствующие хранилища для записи изменений по
     * заданному множеству веб-сайтов.
     */
    public UrlList specification(Map<String, String> y, Map<String, String> t) {
        List<String> listAdded = new ArrayList<>();
        List<String> listChanged = new ArrayList<>();
        for (Map.Entry<String, String> entry : t.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!y.containsKey(key)) {
                listAdded.add(key);
            }
            y.forEach((s1, s2) -> {
                if (s1.equals(key) && !s2.equals(value)) {
                    listChanged.add(key);
                }
            });
            y.remove(key);
        }
        List<String> listLost = new ArrayList<>(y.keySet());
        return new UrlList(listLost, listChanged, listAdded);
    }

    /**
     * Метод позволяет произвести преобразования строк к виду, заявленному в ТЗ.
     * @return возвращает строку для дальнейшего ее переноса в текстовый файл.
     */
    public String appender(UrlList urlList) {
        return new StringBuilder("Здравствуйте, дорогая и.о. секретаря").append(LS)
                .append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:").append(LS)
                .append(ADDED).append(urlList.getListAdded()).append(LS)
                .append(CHANGED).append(urlList.getListChanged()).append(LS)
                .append(LOST).append(urlList.getListLost()).append(LS)
                .append("С уважением, автоматизированная система мониторинга.").toString();
    }

    public static void main(String[] args) {
        ChangesInWebsitesCode changesInWebsitesCode = new ChangesInWebsitesCode();
        var yesterdayCondition = changesInWebsitesCode.mapFilling(websitesY);
        var todayCondition = changesInWebsitesCode.mapFilling(websitesT);
        var url = changesInWebsitesCode.specification(yesterdayCondition, todayCondition);
        try (FileOutputStream out = new FileOutputStream("rsl.txt")) {
            out.write(changesInWebsitesCode.appender(url).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
