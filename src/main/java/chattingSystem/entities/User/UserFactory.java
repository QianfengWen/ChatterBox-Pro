package main.java.chattingSystem.entities.User;

import java.time.LocalDateTime;

public interface UserFactory {
    /**
     * @param username
     * @param userid
     * @param password
     */
    CommonUser create(String username, String userid, String password, LocalDateTime creationTime);
}