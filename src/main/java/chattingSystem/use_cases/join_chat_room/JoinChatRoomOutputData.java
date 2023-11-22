package chattingSystem.use_cases.join_chat_room;

import chattingSystem.entities.User.User;

public class JoinChatRoomOutputData {
    private final User user;
    private final String chatRoomId;
    boolean hasJoined;
    public JoinChatRoomOutputData(User user, boolean hasJoined, String chatRoomId) {
        this.user = user;
        this.hasJoined = hasJoined;
        this.chatRoomId = chatRoomId;
    }

    public User getUser() {
        return user;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }
}
