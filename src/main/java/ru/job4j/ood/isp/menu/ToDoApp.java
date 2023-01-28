package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class ToDoApp {

    private static final String MENU = String.join(System.lineSeparator(), "1. Add an item to the root.",
            "2. Add an item to the parent element.", "3. Print menu.", "4. Exit.");
    private static final ActionDelegate PRINT_ACTION = System.out::println;
    private static final int ROOT_ADD = 1;
    private static final int ADD = 2;
    private static final int PRINT = 3;
    private static final int EXIT = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int select = Integer.parseInt(scanner.nextLine());
            if (ROOT_ADD == select) {
                System.out.println("Enter item's name.");
                menu.add(Menu.ROOT, scanner.nextLine(), PRINT_ACTION);
            } else if (ADD == select) {
                System.out.println("Enter item's root name.");
                var s = scanner.nextLine();
                System.out.println("Enter item's name.");
                menu.add(s, scanner.nextLine(), PRINT_ACTION);
            } else if (PRINT == select) {
                printer.print(menu);
            } else if (EXIT == select) {
                run = false;
            } else {
                System.out.println("Unknown command.");
            }
        }
    }
}
