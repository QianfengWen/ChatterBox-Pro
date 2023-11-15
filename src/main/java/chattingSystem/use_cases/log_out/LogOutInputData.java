package main.java.chattingSystem.use_cases.log_out;

public class LogOutInputData {
    private String username;

    public LogOutInputData(String userId) {
        this.username = userId;
    }

    public String getUsername() {
        return username;
    }
}
