package es.fplumara.dam.rebot.model;

import java.sql.Timestamp;

public record LogEntry(
    Timestamp timestamp,
    String author,
    String content
){

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }


}
