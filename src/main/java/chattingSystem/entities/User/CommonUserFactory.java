package chattingSystem.entities.User;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory{
    @Override
    public User create(String username, String userid, String password, LocalDateTime creationTime) {
        return new CommonUser(username, userid, password, creationTime);
    }
}
