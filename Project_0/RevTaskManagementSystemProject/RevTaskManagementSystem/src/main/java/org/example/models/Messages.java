package org.example.models;

import java.time.LocalDateTime;

public class Messages {
    private int message_id;
    private String message_content;
    private int receiver_id;
    private int sender_id;
    private LocalDateTime timestamp;

    public Messages() {}

    public Messages(int message_id, String message_content,  int receiver_id, int sender_id,LocalDateTime timestamp) {
        this.message_id = message_id;
        this.message_content = message_content;
        this.receiver_id = receiver_id;
        this.sender_id = sender_id;
        this.timestamp = timestamp;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}