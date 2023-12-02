package test;

import chattingSystem.entities.Message.Message;
import chattingSystem.entities.Message.MessageFactory;
import chattingSystem.entities.Message.TextMessage;
import chattingSystem.entities.Message.TextMessageFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class TestMessageEntity {

    private Message message;
    private Message message_use_Factory;
    private String messageID;
    private String senderID;
    private String senderName;

    private LocalDateTime timestamp;
    private String text;

    private MessageFactory messageFactory = new TextMessageFactory();

    @Before
    public void init() {
        messageID = "1";
        senderID = "1";
        senderName = "test";
        timestamp = LocalDateTime.now();
        text = "test";
        message = new TextMessage(messageID, senderID, senderName, timestamp, text);
        message_use_Factory = messageFactory.create(messageID, senderID, senderName, timestamp, text);
    }

    @Test
    public void testGetMethod() {
        assertEquals(message.getMessageID(), messageID);
        assertEquals(message.getSenderID(), senderID);
        assertEquals(message.getSenderName(), senderName);
        assertEquals(message.getCreationTime(), timestamp);
        assertEquals(message.getText(), text);
        assertEquals(message_use_Factory.getMessageID(), messageID);
        assertEquals(message_use_Factory.getSenderID(), senderID);
        assertEquals(message_use_Factory.getSenderName(), senderName);
        assertEquals(message_use_Factory.getCreationTime(), timestamp);
        assertEquals(message_use_Factory.getText(), text);
    }
}
