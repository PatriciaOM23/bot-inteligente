package es.fplumara.dam.rebot.services.files;

import es.fplumara.dam.rebot.config.AppConfig;

public class DefaultFileService implements  FileService{

    @Override
    public void appendLog(String channelId, String entry) {

    }

    @Override
    public String readAll(String channelId) {
        return "";
    }

    @Override
    public String readLast(String channelId, int n) {
        return "";
    }
}
