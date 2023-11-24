package chattingSystem.use_cases.send_message;

import chattingSystem.entities.Message.Message;

import java.io.IOException;
import java.util.List;


public interface SendMessageUserDataAccessInterface {
    String getUsername(String username);

    void save(String id, Message message) throws IOException;

    String generateMessageid(String id);

    List<String> fetchAllMessages();
}
