package es.fplumara.dam.rebot.services.files;

import java.nio.file.Path;

public interface LogStore {
    void appendLog(Path path, String entry) ;
    String readAll(Path path) ;
    String readLast(Path path, int n);
}
