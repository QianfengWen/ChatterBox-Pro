package chattingSystem.use_cases.send_message;

import chattingSystem.entities.Message.Message;

public interface SendMessageUserDataAccessInterface {
    String getUsername(String username);

    void save(String id, Message message);

    String generateMessageid(String id);
}
