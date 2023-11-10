package main.java.chattingSystem.use_cases.sendmessage;

import main.java.chattingSystem.entities.Message.Message;

public interface ChatroomUserDataAccessInterface {
    String get(String username);


    void save(Message message);

    int generateMessageid();
}
