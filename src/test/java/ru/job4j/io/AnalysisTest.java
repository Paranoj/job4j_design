package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {
    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("testServer.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01\n"
                    + "500 10:57:01\n" + "400 10:58:01\n"
                    + "500 11:01:02\n" + "200 11:02:02\n");
        }
        File target  = tempDir.resolve("testTarget.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.toString(), target.toString());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:57:01" +
                "10:58:01;11:01:02").isEqualTo(rsl.toString());
    }
}