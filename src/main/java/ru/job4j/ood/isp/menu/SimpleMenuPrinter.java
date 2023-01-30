package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    private static final String INDENTS = "----";

    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo -> System.out.println(indents(menuItemInfo.getNumber()).
                concat(menuItemInfo.getName())));
    }

    private static String indents(String number) {
        var array = number.split("\\.");
        return INDENTS.repeat(array.length - 1) + number;
    }
}
