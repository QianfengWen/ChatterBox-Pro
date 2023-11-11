package main.java.chattingSystem.use_cases.login;

import main.java.chattingSystem.entities.User.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
