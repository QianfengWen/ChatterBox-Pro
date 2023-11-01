package entity;

import java.time.LocalDateTime;

public class UserFactory {
    /**
     * @param username
     * @param userid
     * @param password
     */

    @Override
    public User create(String username, String userid, String password, LocalDateTime ltd) {
        return new User(username, userid, password, ltd)}
}