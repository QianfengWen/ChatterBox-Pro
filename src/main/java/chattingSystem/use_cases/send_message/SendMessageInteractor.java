package main.java.chattingSystem.use_cases.send_message;

import main.java.chattingSystem.entities.Message.Message;
import main.java.chattingSystem.entities.Message.MessageFactory;

import java.time.LocalDateTime;

public class SendMessageInteractor implements SendMessageInputBoundary {
    final SendMessageUserDataAccessInterface userDataAccessInterface;
    final SendMessageOutputBoundary chatroomMessagePresenter;
    final MessageFactory messageFactory;

    public SendMessageInteractor(SendMessageUserDataAccessInterface userDataAccessInterface,
                                 SendMessageOutputBoundary chatroomMessagePresenter,
                                 MessageFactory messageFactory) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.chatroomMessagePresenter = chatroomMessagePresenter;
        this.messageFactory = messageFactory;
    }

    @Override
    public void execute(SendMessageInputData chatroominputData) {
        LocalDateTime now = LocalDateTime.now();
        int id = userDataAccessInterface.generateMessageid();
        Message message= messageFactory.create(id, chatroominputData.getSenderID(), chatroominputData.getUsername(),
                now, chatroominputData.getMessage());
        userDataAccessInterface.save(message);

        SendMessageOutputData chatroomOutputData = new SendMessageOutputData(false);
        chatroomMessagePresenter.prepareSuccessView(chatroomOutputData);
    }
}
