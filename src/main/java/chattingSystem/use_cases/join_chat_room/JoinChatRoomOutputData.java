package chattingSystem.use_cases.join_chat_room;

import chattingSystem.entities.Message.Message;
import chattingSystem.entities.User.User;

import java.util.List;

public class JoinChatRoomOutputData {
    private final User user;
    private final String chatRoomId;

    private List<String> messages;
    public boolean hasJoined;
    public JoinChatRoomOutputData(User user, boolean hasJoined, String chatRoomId, List<String> messages) {
        this.user = user;
        this.hasJoined = hasJoined;
        this.chatRoomId = chatRoomId;
        this.messages = messages;
    }

    public User getUser() {
        return user;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public List<String> getMessages() {
        return messages;
    }
}
