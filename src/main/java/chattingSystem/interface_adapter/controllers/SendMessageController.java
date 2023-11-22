package chattingSystem.interface_adapter.controllers;

import chattingSystem.use_cases.send_message.SendMessageInputBoundary;
import chattingSystem.use_cases.send_message.SendMessageInputData;
import chattingSystem.use_cases.signup.SignupInputBoundary;
import chattingSystem.use_cases.signup.SignupInputData;

import java.time.LocalDateTime;

public class SendMessageController {
    final SendMessageInputBoundary userSendMessageUseCaseInteractor;
    public SendMessageController(SendMessageInputBoundary userSendMessageUseCaseInteractor) {
        this.userSendMessageUseCaseInteractor = userSendMessageUseCaseInteractor;
    }

    public void execute(String username, String senderID, String message, LocalDateTime timestamp, String chatRoomId) {
        SendMessageInputData sendMessageInputData = new SendMessageInputData(username, senderID, message, timestamp);
        userSendMessageUseCaseInteractor.execute(sendMessageInputData, chatRoomId);
    }
}
