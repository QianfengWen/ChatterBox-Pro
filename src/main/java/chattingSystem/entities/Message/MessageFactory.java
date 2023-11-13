package main.java.chattingSystem.entities.Message;

import java.time.LocalDateTime;

public interface MessageFactory {
    Message create(int messageID, String senderID, String senderName, LocalDateTime timestamp, String text);
}
