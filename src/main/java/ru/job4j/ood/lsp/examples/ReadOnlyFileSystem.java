package ru.job4j.ood.lsp.examples;

import java.io.File;
import java.io.IOException;

public class ReadOnlyFileSystem implements FileSystem {

    @Override
    public File[] listFiles(String path) {
        return new File[0];
    }

    @Override
    public void deleteFile(String path) throws IOException {
        /* Реализация не может выполнить поведение, описанное в интерфейсе. Нарушение LSP.*/
    }
}
