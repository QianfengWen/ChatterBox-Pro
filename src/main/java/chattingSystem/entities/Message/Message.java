package chattingSystem.entities.Message;

import java.time.LocalDateTime;
import java.util.Locale;

public interface Message {
    String getMessageID();
    String getSenderID();
    String getSenderName();
    String getText();
    LocalDateTime getCreationTime();

}
