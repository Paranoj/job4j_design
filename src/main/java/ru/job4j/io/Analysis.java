package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(target))) {
                boolean flag = true;
                while (bufferedReader.ready()) {
                    String readLine = bufferedReader.readLine();
                    if (flag && (readLine.startsWith("400") || readLine.startsWith("500"))) {
                        flag = false;
                        printWriter.print(readLine.split(" ")[1]);
                        printWriter.print(";");
                    }
                    if (!flag && !(readLine.startsWith("300") || readLine.startsWith("400"))) {
                        flag = true;
                        printWriter.println(readLine.split(" ")[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("server.log", "target.txt");
    }
}
