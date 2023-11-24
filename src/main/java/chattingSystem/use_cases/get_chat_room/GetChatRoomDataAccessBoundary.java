package chattingSystem.use_cases.get_chat_room;

import chattingSystem.entities.ChatRoom.ChatRoom;

public interface GetChatRoomDataAccessBoundary {
    ChatRoom getChatRoom(String id);
}
