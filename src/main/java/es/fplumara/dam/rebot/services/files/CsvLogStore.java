package es.fplumara.dam.rebot.services.files;

import java.nio.file.Path;

public class CsvLogStore implements LogStore {
    @Override
    public void appendLog(Path path, String entry) {

    }

    @Override
    public String readAll(Path path) {
        return "";
    }

    @Override
    public String readLast(Path path, int n) {
        return "";
    }
}
