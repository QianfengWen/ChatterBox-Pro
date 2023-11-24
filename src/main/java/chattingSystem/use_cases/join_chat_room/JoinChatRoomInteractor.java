package chattingSystem.use_cases.join_chat_room;


import chattingSystem.entities.ChatRoom.ChatRoom;
import chattingSystem.entities.ChatRoom.ChatRoomFactory;
import chattingSystem.entities.Message.Message;
import chattingSystem.entities.User.User;
import chattingSystem.use_cases.get_chat_room.GetChatRoomDataAccessBoundary;
import chattingSystem.use_cases.get_chat_room.GetUser;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.login.LoginOutputBoundary;
import chattingSystem.use_cases.login.LoginOutputData;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesDataAccessBoundary;
import chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;


import java.io.IOException;
import java.util.List;

public class JoinChatRoomInteractor implements JoinChatRoomInputBoundary {
    private JoinChatRoomOutpurBoundary joinChatRoomOutpurBoundary;
    private JoinChatRoomDataAccessBoundary joinChatRoomDataAccessBoundary;
    private GetChatRoomDataAccessBoundary getChatRoomDataAccessBoundary;
    private LogOutDataAccessBoundary logOutDataAccessBoundary;
    private SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface;

    private RefreshMessagesDataAccessBoundary refreshMessagesDataAccessBoundary;
    private GetUser getUser;
    public JoinChatRoomInteractor(JoinChatRoomOutpurBoundary joinChatRoomOutpurBoundary,
                                  JoinChatRoomDataAccessBoundary joinChatRoomDataAccessBoundary,
                                  GetChatRoomDataAccessBoundary getChatRoomDataAccessBoundary,
                                  LogOutDataAccessBoundary logOutDataAccessBoundary,
                                  GetUser getUser, SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface,
                                  RefreshMessagesDataAccessBoundary refreshMessagesDataAccessBoundary) {
        this.joinChatRoomOutpurBoundary = joinChatRoomOutpurBoundary;
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

                joinChatRoomOutpurBoundary.prepareSuccessView(new JoinChatRoomOutputData(user, true, "1", messagesList), logOutDataAccessBoundary, sendMessageUserDataAccessInterface, refreshMessagesDataAccessBoundary);

            } else {
                joinChatRoomDataAccessBoundary.joinChatRoom(user);
                joinChatRoomOutpurBoundary.prepareSuccessView(new JoinChatRoomOutputData(user, true, "1", messagesList), logOutDataAccessBoundary, sendMessageUserDataAccessInterface, refreshMessagesDataAccessBoundary);

            }

            }
        }
    }
