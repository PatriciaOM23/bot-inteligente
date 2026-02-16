package es.fplumara.dam.rebot.services.files;

import java.io.IOException;

public interface FileService {
    void appendLog(String channelId, String entry) ;
    String readAll(String channelId) ;
    void readLast(String channelId, int n);
}
