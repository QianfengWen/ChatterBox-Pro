package chattingSystem.use_cases.send_message;

public interface SendMessageOutputBoundary {
    void prepareSuccessView(SendMessageOutputData chatroomOutputData);

    void prepareFailView(String error);
}
