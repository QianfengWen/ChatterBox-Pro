package main.java.chattingSystem.use_cases.send_message;

import main.java.chattingSystem.entities.Message.Message;
import main.java.chattingSystem.entities.Message.MessageFactory;

import java.time.LocalDateTime;

public class SendMessageInteractor implements SendMessageInputBoundary {
    final SendMessageUserDataAccessInterface userDataAccessInterface;
    final SendMessageOutputBoundary chatroomMessagePresenter;
    final MessageFactory messageFactory;

    public SendMessageInteractor(SendMessageUserDataAccessInterface userDataAccessInterface,
                                 SendMessageOutputBoundary sendMessagePresenter,
                                 MessageFactory messageFactory) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.chatroomMessagePresenter = sendMessagePresenter;
        this.messageFactory = messageFactory;
    }

    @Override
    public void execute(SendMessageInputData sendMessageInputData, String chatRoomId) {
        LocalDateTime now = LocalDateTime.now();
        String id = userDataAccessInterface.generateMessageid(chatRoomId);
        Message message = messageFactory.create(id, sendMessageInputData.getSenderID(),
                sendMessageInputData.getUsername(), now, sendMessageInputData.getMessage());
        userDataAccessInterface.save(chatRoomId, message);

        // need some change to connect with DataAccess.
        SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(sendMessageInputData.getSenderID(),
                sendMessageInputData.getUsername(), chatRoomId, sendMessageInputData.getMessage(), false);
        chatroomMessagePresenter.prepareSuccessView(sendMessageOutputData);
    }
}
