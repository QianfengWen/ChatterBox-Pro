package main.java.chattingSystem.use_cases.sendmessage;

public interface ChatroommessageOutputBoundary {
    void prepareSuccessView(ChatroomOutputData chatroomOutputData);

    void prepareFailView(String error);
}
