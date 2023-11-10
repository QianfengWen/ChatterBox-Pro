package main.java.chattingSystem.entities.Message;

import java.time.LocalDateTime;
import java.util.Locale;

public interface Message {
    int getMessageID();
    String getSenderID();
    String getSenderName();
    String getText();
    LocalDateTime getCreationTime();

}
