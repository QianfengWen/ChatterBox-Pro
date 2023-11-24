package chattingSystem.entities.ChatRoom;

import chattingSystem.entities.Message.TextMessage;
import chattingSystem.entities.User.CommonUser;
import chattingSystem.entities.User.User;


import java.time.LocalDateTime;
import java.util.List;

public class CommonChatRoom implements ChatRoom {
    private final List<User> members;

    private final String chatRoomId;

    private LocalDateTime creationTime;


    CommonChatRoom(String chatRoomId, List<User> Members, LocalDateTime creationTime) {
        this.chatRoomId = chatRoomId;
        this.members = Members;
        this.creationTime = creationTime;
    }

    @Override
    public List<User> getMembers() {
        return members;
    }

    @Override
    public String getMembersString() {
        StringBuilder memberString = new StringBuilder();
        for (User user : members) {
            memberString.append(user.getUsername()).append(" ");
        }
        return memberString.toString();
    }

    @Override
    public String getMembersIdString() {
        StringBuilder memberString = new StringBuilder();
        for (User user : members) {
            // The same bug as in ChatRoomDataAccessObject.java
            if (user == null) {
                // This handles when the admin was set to null when the program runs not for the first time.
                continue;
            }
            memberString.append(user.getUserid()).append(" ");
        }
        return memberString.toString();
    }

    @Override
    public String getChatHistoryString() {
//        StringBuilder chatHistoryString = new StringBuilder();
//        for (TextMessage message : chatHistory) {
//            chatHistoryString.append(message.toString()).append("\n");
//        }
//        return chatHistoryString.toString();
        return "Test";
    }

    @Override
    public void addMember(User user) {
        members.add(user);
    }

    @Override
    public String getChatRoomID() {
        return chatRoomId;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }


}
