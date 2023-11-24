package chattingSystem.entities.Message;

import java.time.LocalDateTime;

public class TextMessage implements Message{
    String messageID;
    private final String senderID;

    private final String senderName;
    private final LocalDateTime timestamp;
    private final String text;

    public TextMessage(String messageID, String senderID, String senderName, LocalDateTime timestamp, String text) {
        this.messageID = messageID;
        this.senderID = senderID;
        this.senderName = senderName;
        this.timestamp = timestamp;
        this.text = text;
    }

    @Override
    public String getMessageID() {
        return messageID;
    }

    @Override
    public String getSenderID() {
        return senderID;
    }

    @Override
    public String getSenderName() {
        return senderName;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return timestamp;
    }
}
