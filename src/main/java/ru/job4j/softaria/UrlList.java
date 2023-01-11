package ru.job4j.softaria;

import java.util.List;

public record UrlList(List<String> listLost, List<String> listChanged,
                      List<String> listAdded) {

    /**
     * Созданы хранилища для записи URL потерянных, обновленных и добавленных сайтов.
     */
    public List<String> getListLost() {
        return listLost;
    }

    public List<String> getListChanged() {
        return listChanged;
    }

    public List<String> getListAdded() {
        return listAdded;
    }
}
