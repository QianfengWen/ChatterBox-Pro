package test;

import chattingSystem.use_cases.send_message.SendMessageInputData;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class TestSendMessageInputData {
    private String username;
    private String senderID;
    private String message;
    private LocalDateTime timestamp;
    private SendMessageInputData sendMessageInputData;

    @Before
    public void init() {
        username = "test";
        senderID = "1";
        message = "test";
        timestamp = LocalDateTime.now();
        sendMessageInputData = new SendMessageInputData(username, senderID, message, timestamp);
    }

    @Test
    public void testGetmethod() {
        assertEquals(sendMessageInputData.getMessage(), message);
        assertEquals(sendMessageInputData.getUsername(), username);
        assertEquals(sendMessageInputData.getTimestap(), timestamp);
        assertEquals(sendMessageInputData.getSenderID(), senderID);
    }

}
