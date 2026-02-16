package es.fplumara.dam.rebot.services.files;

import es.fplumara.dam.rebot.exceptions.ConfigException;

public class LogStoreFactory {
    public LogStore createLog (String extension){
        switch (extension){
            case "TXT" -> {
                return new TxtLogStore();
            }
            case "CSV" -> {
                return new CsvLogStore();
            }
            default -> throw new ConfigException("The extension provided was not found");

        }
    }
}
