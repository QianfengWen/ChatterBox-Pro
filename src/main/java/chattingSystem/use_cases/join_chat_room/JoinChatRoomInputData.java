package chattingSystem.use_cases.join_chat_room;

import chattingSystem.entities.User.User;

public class JoinChatRoomInputData {
    private String username;

    public JoinChatRoomInputData(String user) {
        this.username = user;
    }

    public String getUsername() {
        return username;
    }
}
