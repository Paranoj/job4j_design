package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    private static final String INDENTS = "----";

    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo -> System.out.println(indents(menuItemInfo.getNumber()).
                concat(menuItemInfo.getName())));
    }

    private static String indents(String number) {
        int count = -1;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '.') {
                count++;
            }
        }
        return INDENTS.repeat(count) + number;
    }
}
