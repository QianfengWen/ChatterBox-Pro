package chattingSystem.use_cases.join_chat_room;


import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesDataAccessBoundary;
import chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;


import java.io.IOException;

public interface JoinChatRoomOutputBoundary {
    void prepareSuccessView(JoinChatRoomOutputData joinChatRoomOutputData,
                            LogOutDataAccessBoundary logOutDataAccessBoundary,
                            SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface,
                            RefreshMessagesDataAccessBoundary refreshMessagesDataAccessBoundary) throws IOException;

    void prepareFailView(JoinChatRoomOutputData joinChatRoomOutputData);
}
