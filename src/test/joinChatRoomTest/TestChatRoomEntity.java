package joinChatRoomTest;

import chattingSystem.entities.ChatRoom.ChatRoom;
import chattingSystem.entities.ChatRoom.ChatRoomFactory;
import chattingSystem.entities.ChatRoom.CommonChatRoom;
import chattingSystem.entities.ChatRoom.CommonChatRoomFactory;
import chattingSystem.entities.User.CommonUserFactory;
import chattingSystem.entities.User.User;
import chattingSystem.entities.User.UserFactory;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestChatRoomEntity {
    private List<User> members;

    private String chatRoomId;

    private LocalDateTime creationTime;

    private ChatRoomFactory chatRoomFactory = new CommonChatRoomFactory();
    private ChatRoom chatRoom_use_factory;

    private User user1;
    private User user2;

    @Before
    public void init() {
        chatRoomId = "1";
        creationTime = LocalDateTime.now();
        UserFactory userFactory = new CommonUserFactory();
        user1 = userFactory.create("User1", "1", "Qwe123123", LocalDateTime.now());
        user2 = userFactory.create("User2", "1", "Qwe123123", LocalDateTime.now());
        members = new ArrayList<>();
        chatRoom_use_factory = chatRoomFactory.create(chatRoomId, members, creationTime);
    }

    @Test
    public void testEditMember() {
        assertEquals(chatRoom_use_factory.getMembers().size(), 0);
        chatRoom_use_factory.addMember(user1);
        assertEquals(chatRoom_use_factory.getMembers().get(0), user1);
        chatRoom_use_factory.addMember(user2);
        assertEquals(chatRoom_use_factory.getMembers().get(0), user1);
        assertEquals(chatRoom_use_factory.getMembers().get(1), user2);
    }

    @Test
    public void testGetMethod() {
        assertEquals(chatRoom_use_factory.getChatRoomID(), chatRoomId);
        assertEquals(chatRoom_use_factory.getMembersString().length(), 0);
        assertEquals(chatRoom_use_factory.getMembersIdString().length(), 0);
        chatRoom_use_factory.addMember(user1);
        assertEquals(chatRoom_use_factory.getMembersString(), user1.getUsername() + " ");
        assertEquals(chatRoom_use_factory.getMembersIdString(), user1.getUserid() + " ");
        chatRoom_use_factory.addMember(user2);
        assertEquals(chatRoom_use_factory.getMembersString(), user1.getUsername() + " " +
                user2.getUsername() + " ");
        assertEquals(chatRoom_use_factory.getCreationTime(), creationTime);
        assertEquals(chatRoom_use_factory.getMembersIdString(), user1.getUserid() + " " +
                user2.getUserid() + " ");
    }
}
