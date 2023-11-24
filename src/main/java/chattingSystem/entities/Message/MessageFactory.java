package chattingSystem.entities.Message;

import java.time.LocalDateTime;

public interface MessageFactory {
    Message create(String messageID, String senderID, String senderName, LocalDateTime timestamp, String text);
}
