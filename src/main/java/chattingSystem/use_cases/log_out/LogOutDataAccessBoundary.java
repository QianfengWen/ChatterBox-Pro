package main.java.chattingSystem.use_cases.log_out;
import main.java.chattingSystem.entities.User.User;

public interface LogOutDataAccessBoundary {
    void logOut(String username);
    User get(String username);
}
