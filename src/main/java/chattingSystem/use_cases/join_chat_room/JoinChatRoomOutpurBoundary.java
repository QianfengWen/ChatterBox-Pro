package main.java.chattingSystem.use_cases.join_chat_room;

import main.java.chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import main.java.chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;

import java.io.IOException;

public interface JoinChatRoomOutpurBoundary {
    void prepareSuccessView(JoinChatRoomOutputData joinChatRoomOutputData, LogOutDataAccessBoundary logOutDataAccessBoundary, SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface) throws IOException;

    void prepareFailView(JoinChatRoomOutputData joinChatRoomOutputData);
}
