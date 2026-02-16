package es.fplumara.dam.rebot.services.files;

import es.fplumara.dam.rebot.model.LogEntry;

import java.nio.file.Path;

public interface LogStore {
    void appendLog(Path path, LogEntry entry) ;
    String readAll(Path path) ;
    String readLast(Path path, int n);
}
