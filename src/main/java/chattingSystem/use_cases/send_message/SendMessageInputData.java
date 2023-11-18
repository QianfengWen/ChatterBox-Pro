package main.java.chattingSystem.use_cases.send_message;

import java.time.LocalDateTime;

public class SendMessageInputData {
    final private String username;
    final private String senderID;
    final private String message;
    final private LocalDateTime timestamp;

    public SendMessageInputData(String username, String senderID, String message, LocalDateTime timestamp) {
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
