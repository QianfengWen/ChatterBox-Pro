package StateTests;

import chattingSystem.interface_adapter.state.ChatRoomState;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomStateTest {

    @Test
    void setUsernameTest() {
        ChatRoomState chatRoomState = new ChatRoomState();
        chatRoomState.setUsername("testUser");
        assertEquals("testUser", chatRoomState.getUsername());
    }

    @Test
    void getUsernameTest() {
        ChatRoomState chatRoomState = new ChatRoomState();
        chatRoomState.setUsername("testUser");
        String result = chatRoomState.getUsername();
        assertEquals("testUser", result);
    }

    @Test
    void setChatRoomIdTest() {
        ChatRoomState chatRoomState = new ChatRoomState();
        chatRoomState.setChatRoomId("1");
        assertEquals("1", chatRoomState.getChatRoomId());
    }

    @Test
    void getChatRoomIdTest() {
        ChatRoomState chatRoomState = new ChatRoomState();
        chatRoomState.setChatRoomId("1");
        String result = chatRoomState.getChatRoomId();
        assertEquals("1", result);
    }

    @Test
    void setSenderIdTest() {
        ChatRoomState chatRoomState = new ChatRoomState();
        chatRoomState.setSenderId("1");
        assertEquals("1", chatRoomState.getSenderId());
    }

    @Test
    void getSenderIdTest() {
        ChatRoomState chatRoomState = new ChatRoomState();
        chatRoomState.setSenderId("1");
        String result = chatRoomState.getSenderId();
        assertEquals("1", result);
    }

    @Test
    void setMessageHistoryTest() {
        ChatRoomState chatRoomState = new ChatRoomState();
        chatRoomState.setMessage(List.of("Hello", "Hello"));
        List<String> result = chatRoomState.getMessageHistory();
        assertEquals(List.of("Hello", "Hello"), result);
    }

    @Test
    void getMessageHistoryTest() {
        ChatRoomState chatRoomState = new ChatRoomState();
        chatRoomState.setMessage(List.of("Hello", "Hello"));
        List<String> result = chatRoomState.getMessageHistory();
        assertEquals(List.of("Hello", "Hello"), result);
    }
}
