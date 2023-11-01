package main.java.chattingSystem.entities.Message;

import java.util.Date;

public class TextMessage extends Message{
    private final String text;

    public TextMessage(int messageID, String senderID, String senderName, Date timestamp, String text){
        super(messageID, senderID, senderName, timestamp);
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
