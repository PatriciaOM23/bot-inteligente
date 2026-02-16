package es.fplumara.dam.rebot.model;

import java.sql.Timestamp;

public record LogEntry(
    Timestamp timestamp,
    String author,
    String content
){
    // LOS RECORDS TIENEN GETTERS POR DEFECTO

}
