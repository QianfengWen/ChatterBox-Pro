package chattingSystem.use_cases.login;

import chattingSystem.entities.User.User;

import java.io.IOException;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user) throws IOException;

    User get(String username);
}
