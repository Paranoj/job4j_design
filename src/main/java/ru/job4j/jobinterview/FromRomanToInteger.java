package ru.job4j.jobinterview;

public class FromRomanToInteger {
    public static void main(String[] args) {
        String s = "MCMXCIV";
        int number, fin = 0, prev = switch (s.charAt(0)) {
            case 'M' -> number = 1000;
            case 'D' -> number = 500;
            case 'C' -> number = 100;
            case 'L' -> number = 50;
            case 'X' -> number = 10;
            case 'V' -> number = 5;
            case 'I' -> number = 1;
            default -> throw new IllegalStateException("Unexpected value: " + s.charAt(0));
        };
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M' -> number = 1000;
                case 'D' -> number = 500;
                case 'C' -> number = 100;
                case 'L' -> number = 50;
                case 'X' -> number = 10;
                case 'V' -> number = 5;
                case 'I' -> number = 1;
            }
            if (prev < number) {
                fin -= prev * 2;
            }
            fin += number;
            prev = number;
        }
        System.out.println(fin);
    }
}
