package es.fplumara.dam.rebot.services.files;

import es.fplumara.dam.rebot.model.LogEntry;

import java.io.IOException;

public interface FileService {
    void appendLog(String channelId, LogEntry entry) ;
    String readAll(String channelId) ;
    String readLast(String channelId, int n);
}
