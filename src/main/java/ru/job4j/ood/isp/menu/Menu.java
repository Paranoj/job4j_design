package ru.job4j.ood.isp.menu;

import java.util.Optional;

public interface Menu extends Iterable<MenuItemInfo> {

    String ROOT = null; /* Константа, указывающая, что нужно добавить элемент в корень */

    boolean add(String parentName, String childName, ActionDelegate actionDelegate);

    Optional<MenuItemInfo> select(String itemName);
}
