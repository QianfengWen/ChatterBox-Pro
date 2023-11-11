package main.java.chattingSystem.frameworks_drivers.data_access;


import main.java.chattingSystem.entities.ChatRoom.ChatRoom;
import main.java.chattingSystem.entities.User.User;
import main.java.chattingSystem.use_cases.create_chat_room.CreateChatRoomDataAccessBoundary;
import main.java.chattingSystem.use_cases.get_chat_room.GetChatRoomDataAccessBoundary;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomDataAccessBoundary;

import java.util.HashMap;
import java.util.Map;

public class ChatRoomDataAccessObject implements CreateChatRoomDataAccessBoundary, GetChatRoomDataAccessBoundary, JoinChatRoomDataAccessBoundary {
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();

    public ChatRoomDataAccessObject() {
    }

    @Override
    public boolean existsById(String id) {
        return chatRooms.containsKey(id);
    }

    @Override
    public void joinChatRoom(User user) {
        ChatRoom chatRoom = chatRooms.get("0");
        chatRoom.addMember(user);
    }

    @Override
    public void save(ChatRoom chatRoom) {
        chatRooms.put(chatRoom.getChatRoomID(), chatRoom);
    }

    @Override
    public ChatRoom getChatRoom(String id) {
        return chatRooms.get(id);
    }
}
