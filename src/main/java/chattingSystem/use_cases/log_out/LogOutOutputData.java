package chattingSystem.use_cases.log_out;

public class LogOutOutputData {
    private final String username;
    private final boolean hasLoggedOut;

    public LogOutOutputData(String username, boolean hasLoggedOut) {
        this.username = username;
        this.hasLoggedOut = hasLoggedOut;
    }

    public String getUsername() {
        return username;
    }

    public boolean getHasLoggedOut() {
        return hasLoggedOut;
    }
}


