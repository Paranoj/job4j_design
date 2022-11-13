package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] array = new int[5][5];
            for (int row = 0; row < 5; row++) {
                for (int cell = 0; cell < 5; cell++) {
                    var arr = array[row][cell];
                    arr = (row + 1) * (cell + 1);
                    out.write((arr + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
