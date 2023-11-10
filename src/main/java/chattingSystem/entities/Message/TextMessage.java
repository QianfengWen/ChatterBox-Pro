package main.java.chattingSystem.entities.Message;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class Message {
    int messageID;
    private String senderID;

    private String senderName;
    private LocalDateTime timestamp;

    public Message(int messageID, String senderID, String senderName, LocalDateTime timestamp) {
        this.messageID = messageID;
        this.senderID = senderID;
        this.senderName = senderName;
        this.timestamp = timestamp;
    }

}
