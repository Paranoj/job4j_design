package ru.job4j.jobinterview;

import java.util.Scanner;

public class EncodeString {
    public static char[] compression(String s) {
        int count = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                count++;
            } else {
                stringBuilder.append(s.charAt(i - 1));
                stringBuilder.append(count);
                count = 1;
            }
            if (i == s.length() - 1) {
                stringBuilder.append(s.charAt(i));
                stringBuilder.append(count);
            }
        }
        if (stringBuilder.isEmpty()) {
            throw new IllegalArgumentException("Unacceptable string length.");
        }
        return stringBuilder.toString().toCharArray();
    }

    public static void main(String[] args) {
        String s1;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a string");
        s1 = in.nextLine();
        System.out.println(compression(s1));
    }
}
