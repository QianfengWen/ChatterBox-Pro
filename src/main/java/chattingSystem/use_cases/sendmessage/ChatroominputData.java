package main.java.chattingSystem.use_cases.sendmessage;

import java.time.LocalDateTime;

public class ChatroominputData {
    final private String username;
    final private String senderID;
    final private String message;
    final private LocalDateTime timestamp;

    public ChatroominputData(String username, String senderID, String message, LocalDateTime timestamp) {
        this.username = username;
        this.senderID = senderID;
        this.message = message;
        this.timestamp = timestamp;
    }

    String getUsername() {return username;}
    String getSenderID() {return senderID;}
    String getMessage() {return message;}
    LocalDateTime getTimestap() {return timestamp;}
}
