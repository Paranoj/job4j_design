package ru.job4j.softaria;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ChangesWebsitesCode implements Changes {

    private static final String LOST = "Исчезли следующие страницы: ";
    private static final String ADDED = "Появились следующие новые страницы: ";
    private static final String CHANGED = "Изменились следующие страницы: ";
    private static final String LS = System.lineSeparator();
    private static final String HTML_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";

    /**
     * Метод производит валидацию хранилища объектов Web по наличию значений
     * в нём. Проверяет что ключом хэш-таблицы является URL-ссылка, а значением HTML-код.
     */
    private void validation(Map<String, String> map) {
        Pattern pattern = Pattern.compile(HTML_PATTERN);
        if (map.isEmpty()) {
            throw new IllegalArgumentException("Map is empty.");
        }
        for (String key : map.keySet()) {
            try {
                new URL(key);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException(String.format(
                        "Map must consist of url as key: %s", key
                ));
            }
            if (!pattern.matcher(map.get(key)).find()) {
                throw new IllegalArgumentException(String.format("Map must consist of html as value: %s",
                        map.get(key)));
            }
        }
    }

    /**
     * Метод принимает на вход 2 хэш-таблицы, содержащие состояния сайтов на вчера и сегодня соответственно.
     * Производится добавление URL-ов в соответствующие хранилища для записи изменений по
     * заданному множеству веб-сайтов.
     */
    private UrlList specification(Map<String, String> y, Map<String, String> t) {
        validation(y);
        validation(t);
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
    private String appender(UrlList urlList) {
        return new StringBuilder("Здравствуйте, дорогая и.о. секретаря").append(LS)
                .append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:").append(LS)
                .append(ADDED).append(urlList.listAdded()).append(LS)
                .append(CHANGED).append(urlList.listChanged()).append(LS)
                .append(LOST).append(urlList.listLost()).append(LS)
                .append("С уважением, автоматизированная система мониторинга.").toString();
    }

    /**
     * @param y Список веб-сайтов за вчера.
     * @param t Список веб-сайтов за сегодня.
     * @return Возвращает строку в виде письма в формате, представленном в ТЗ.
     */
    @Override
    public String letter(Map<String, String> y, Map<String, String> t) {
        var url = specification(y, t);
        return appender(url);
    }
}
