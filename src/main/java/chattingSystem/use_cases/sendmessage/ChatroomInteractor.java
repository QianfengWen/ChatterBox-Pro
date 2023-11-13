package main.java.chattingSystem.use_cases.sendmessage;

import main.java.chattingSystem.entities.Message.Message;
import main.java.chattingSystem.entities.Message.MessageFactory;
import main.java.chattingSystem.entities.User.User;
import main.java.chattingSystem.use_cases.signup.SignupOutputData;

import java.time.LocalDateTime;

public class ChatroomInteractor implements ChatroommessageInputBoundary{
    final ChatroomUserDataAccessInterface userDataAccessInterface;
    final ChatroommessageOutputBoundary chatroomMessagePresenter;
    final MessageFactory messageFactory;

    public ChatroomInteractor(ChatroomUserDataAccessInterface userDataAccessInterface,
                              ChatroommessageOutputBoundary chatroomMessagePresenter,
                              MessageFactory messageFactory) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.chatroomMessagePresenter = chatroomMessagePresenter;
        this.messageFactory = messageFactory;
    }

    @Override
    public void execute(ChatroominputData chatroominputData) {
        LocalDateTime now = LocalDateTime.now();
        int id = userDataAccessInterface.generateMessageid();
        Message message= messageFactory.create(id, chatroominputData.getSenderID(), chatroominputData.getUsername(),
                now, chatroominputData.getMessage());
        userDataAccessInterface.save(message);

        ChatroomOutputData chatroomOutputData = new ChatroomOutputData(false);
        chatroomMessagePresenter.prepareSuccessView(chatroomOutputData);
    }
}
