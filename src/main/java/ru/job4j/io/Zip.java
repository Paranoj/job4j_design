package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        File file = new File(argsName.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "The directory to be archived doesn't exist: %s", file.getAbsoluteFile()));
        }
        if (!argsName.get("d").substring(1).startsWith(":\\")) {
            throw new IllegalArgumentException(String.format(
                    "The directory must start with :\\ - %s", argsName.get("d")));
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format(
                    "The exclusion must start with dot - %s", argsName.get("e")));
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format(
                    "The output must end with an extension .zip - %s", argsName.get("o")));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong number of parameters to realize the program.");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        List<Path> list = Search.search(Path.of(argsName.get("d")),
                path -> !path.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(list, new File(argsName.get("o")));
    }
}
