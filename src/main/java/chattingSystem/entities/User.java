package entity;

import java.time.LocalDateTime;

class User {
    private final String username;
    private final String userid;
    private final String password;
    private final LocalDateTime creationTime;
    /**
     * @param username
     * @param userid
     * @param password
     */
    User(String username, String userid, String password, LocalDateTime creationTime) {
        this.username = username;
        this.userid = userid;
        this.password = password;
        this.creationTime= creationTime;
    }
    @Override
    public String getUsername(){return username};
    @Override
    public String getUserid(){return userid};
    @Override
    public String getPassword(){return password};
    @Override
    public LocalDateTime getCreationTime(){return creationTime};
}
