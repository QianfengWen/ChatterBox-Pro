package chattingSystem.entities.User;

import java.time.LocalDateTime;

public class CommonUser implements User{
    private final String username;
    private final String userid;
    private final String password;
    private boolean isOnline = false;
    private final LocalDateTime creationTime;
    /**
     * @param username
     * @param userid
     * @param password
     * @param creationTime
     */
    public CommonUser(String username, String userid, String password, LocalDateTime creationTime) {
        this.username = username;
        this.userid = userid;
        this.password = password;
        this.creationTime= creationTime;
    }
    @Override
    public String getUsername(){return username;};
    @Override
    public String getUserid(){return userid;};
    @Override
    public String getPassword(){return password;};
    @Override
    public LocalDateTime getCreationTime(){return creationTime;};
    @Override
    public boolean getIsOnline(){return isOnline;};
    @Override
    public void setOnline(boolean isOnline){
        this.isOnline = isOnline;
    };
}
