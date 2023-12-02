package test;

import chattingSystem.use_cases.send_message.SendMessageOutputData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSendMessageOutputData {
    private boolean useCaseFailed;

    private String chatRoomId;

    private String senderId;

    private String username;

    private String message;
    private SendMessageOutputData sendMessageOutputData;

   @Before
   public void init() {
       senderId = "1";
       username = "test";
       message = "test";
       chatRoomId = "1";
       useCaseFailed = false;
       sendMessageOutputData = new SendMessageOutputData(senderId, username, chatRoomId, message, useCaseFailed);
   }

   @Test
    public void testGetMethod() {
       assertEquals(sendMessageOutputData.getMessage(), message);
       assertEquals(sendMessageOutputData.getUsername(), username);
       assertEquals(sendMessageOutputData.getSenderId(), senderId);
       assertEquals(sendMessageOutputData.getChatRoomId(), chatRoomId);
   }
}
