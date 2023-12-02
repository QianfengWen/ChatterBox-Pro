package chattingSystem.use_cases.join_chat_room;


import chattingSystem.entities.ChatRoom.ChatRoom;
import chattingSystem.entities.User.User;
import chattingSystem.use_cases.get_chat_room.GetChatRoomDataAccessBoundary;
import chattingSystem.use_cases.get_chat_room.GetUser;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesDataAccessBoundary;
import chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;


import java.io.IOException;
import java.util.List;

public class JoinChatRoomInteractor implements JoinChatRoomInputBoundary {
    private JoinChatRoomOutputBoundary joinChatRoomOutputBoundary;
    private JoinChatRoomDataAccessBoundary joinChatRoomDataAccessBoundary;
    private GetChatRoomDataAccessBoundary getChatRoomDataAccessBoundary;
    private LogOutDataAccessBoundary logOutDataAccessBoundary;
    private SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface;

    private RefreshMessagesDataAccessBoundary refreshMessagesDataAccessBoundary;
    private GetUser getUser;
    public JoinChatRoomInteractor(JoinChatRoomOutputBoundary joinChatRoomOutputBoundary,
                                  JoinChatRoomDataAccessBoundary joinChatRoomDataAccessBoundary,
                                  GetChatRoomDataAccessBoundary getChatRoomDataAccessBoundary,
                                  LogOutDataAccessBoundary logOutDataAccessBoundary,
                                  GetUser getUser, SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface,
                                  RefreshMessagesDataAccessBoundary refreshMessagesDataAccessBoundary) {
        this.joinChatRoomOutputBoundary = joinChatRoomOutputBoundary;
        this.joinChatRoomDataAccessBoundary = joinChatRoomDataAccessBoundary;
        this.getChatRoomDataAccessBoundary = getChatRoomDataAccessBoundary;
        this.logOutDataAccessBoundary = logOutDataAccessBoundary;
        this.sendMessageUserDataAccessInterface = sendMessageUserDataAccessInterface;
        this.refreshMessagesDataAccessBoundary = refreshMessagesDataAccessBoundary;
        this.getUser = getUser;
    }


    @Override
    public void execute(JoinChatRoomInputData inputData) throws IOException {
        // join chat room
        String username = inputData.getUsername();;
        if (!joinChatRoomDataAccessBoundary.existsById("1")) {
            System.out.println("Chat room does not exist.");
        } else {
            ChatRoom chatRoom = getChatRoomDataAccessBoundary.getChatRoom("1");
            User user = getUser.getUser(username);
            List<String> messagesList = joinChatRoomDataAccessBoundary.fetchAllMessages();
            if (chatRoom.getMembers().contains(user)) {

                joinChatRoomOutputBoundary.prepareSuccessView(new JoinChatRoomOutputData(user, true, "1", messagesList), logOutDataAccessBoundary, sendMessageUserDataAccessInterface, refreshMessagesDataAccessBoundary);

            } else {
                joinChatRoomDataAccessBoundary.joinChatRoom(user);
                joinChatRoomOutputBoundary.prepareSuccessView(new JoinChatRoomOutputData(user, true, "1", messagesList), logOutDataAccessBoundary, sendMessageUserDataAccessInterface, refreshMessagesDataAccessBoundary);

            }

            }
        }
    }
