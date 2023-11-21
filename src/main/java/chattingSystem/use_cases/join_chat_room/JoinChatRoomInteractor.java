package main.java.chattingSystem.use_cases.join_chat_room;

import main.java.chattingSystem.entities.ChatRoom.ChatRoom;
import main.java.chattingSystem.entities.ChatRoom.ChatRoomFactory;
import main.java.chattingSystem.entities.User.User;
import main.java.chattingSystem.use_cases.get_chat_room.GetChatRoomDataAccessBoundary;
import main.java.chattingSystem.use_cases.get_chat_room.GetUser;
import main.java.chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import main.java.chattingSystem.use_cases.login.LoginOutputBoundary;
import main.java.chattingSystem.use_cases.login.LoginOutputData;
import main.java.chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;

import java.io.IOException;

public class JoinChatRoomInteractor implements JoinChatRoomInputBoundary {
    private JoinChatRoomOutpurBoundary joinChatRoomOutpurBoundary;
    private JoinChatRoomDataAccessBoundary joinChatRoomDataAccessBoundary;
    private GetChatRoomDataAccessBoundary getChatRoomDataAccessBoundary;
    private LogOutDataAccessBoundary logOutDataAccessBoundary;
    private SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface;
    private GetUser getUser;
    public JoinChatRoomInteractor(JoinChatRoomOutpurBoundary joinChatRoomOutpurBoundary,
                                  JoinChatRoomDataAccessBoundary joinChatRoomDataAccessBoundary,
                                  GetChatRoomDataAccessBoundary getChatRoomDataAccessBoundary,
                                  LogOutDataAccessBoundary logOutDataAccessBoundary,
                                  GetUser getUser,
                                  SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface) {
        this.joinChatRoomOutpurBoundary = joinChatRoomOutpurBoundary;
        this.joinChatRoomDataAccessBoundary = joinChatRoomDataAccessBoundary;
        this.getChatRoomDataAccessBoundary = getChatRoomDataAccessBoundary;
        this.logOutDataAccessBoundary = logOutDataAccessBoundary;
        this.getUser = getUser;
        this.sendMessageUserDataAccessInterface = sendMessageUserDataAccessInterface;
    }


    @Override
    public void execute(JoinChatRoomInputData inputData) throws IOException {
        // join chat room
        String username = inputData.getUsername();;
        if (!joinChatRoomDataAccessBoundary.existsById("0")) {
            System.out.println("Chat room does not exist.");
        } else {
            ChatRoom chatRoom = getChatRoomDataAccessBoundary.getChatRoom("0");
            User user = getUser.getUser(username);
            if (chatRoom.getMembers().contains(user)) {
                joinChatRoomOutpurBoundary.prepareSuccessView(new JoinChatRoomOutputData(user, true, "0"), logOutDataAccessBoundary, sendMessageUserDataAccessInterface);

            } else {
                joinChatRoomDataAccessBoundary.joinChatRoom(user);
                joinChatRoomOutpurBoundary.prepareSuccessView(new JoinChatRoomOutputData(user, true, "0"), logOutDataAccessBoundary, sendMessageUserDataAccessInterface);
            }

            }
        }
    }
