package chattingSystem.use_cases.signup;

import chattingSystem.entities.User.User;

import java.io.IOException;

public interface SignupUserDataAccessInterface {
    User get(String username);

    boolean existsByName(String identifier);

    void save(User user) throws IOException;

    String generateUserid();
}
