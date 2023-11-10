package main.java.chattingSystem.use_cases.signup;

import main.java.chattingSystem.entities.User.User;

public interface SignupUserDataAccessInterface {
    User get(String username);

    boolean existsByName(String identifier);

    void save(User user);

    String generateUserid();
}
