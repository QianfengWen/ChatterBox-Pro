package chattingSystem.use_cases.send_message;

import chattingSystem.entities.Message.Message;
import chattingSystem.entities.Message.MessageFactory;

import java.io.IOException;
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
    public void execute(SendMessageInputData sendMessageInputData, String chatRoomId) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String id = userDataAccessInterface.generateMessageid(chatRoomId);
        Message message = messageFactory.create(id, sendMessageInputData.getSenderID(),
                sendMessageInputData.getUsername(), now, sendMessageInputData.getMessage());
        userDataAccessInterface.fetchAllMessages();
        userDataAccessInterface.save(id, message);

        // need some change to connect with DataAccess.
        SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(sendMessageInputData.getSenderID(),
                sendMessageInputData.getUsername(), chatRoomId, sendMessageInputData.getMessage(), false);
        chatroomMessagePresenter.prepareSuccessView(sendMessageOutputData);
    }
}
