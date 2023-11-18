package main.java.chattingSystem.use_cases.send_message;

import main.java.chattingSystem.entities.Message.Message;

public interface SendMessageUserDataAccessInterface {
    String get(String username);


    void save(Message message);

    int generateMessageid();
}
