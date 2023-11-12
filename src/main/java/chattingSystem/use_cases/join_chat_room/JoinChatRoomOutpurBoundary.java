package main.java.chattingSystem.use_cases.join_chat_room;

import java.io.IOException;

public interface JoinChatRoomOutpurBoundary {
    void prepareSuccessView(JoinChatRoomOutputData joinChatRoomOutputData) throws IOException;

    void prepareFailView(JoinChatRoomOutputData joinChatRoomOutputData);
}
