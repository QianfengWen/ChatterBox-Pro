package chattingSystem.frameworks_drivers.data_access;

import chattingSystem.entities.User.User;
import chattingSystem.entities.User.UserFactory;
import chattingSystem.frameworks_drivers.api.MongoDownloadUsers;
import chattingSystem.frameworks_drivers.api.MongoUploadUser;
import chattingSystem.interface_adapter.state.ChatRoomState;
import chattingSystem.use_cases.get_chat_room.GetUser;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.login.LoginUserDataAccessInterface;
import chattingSystem.use_cases.signup.SignupUserDataAccessInterface;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, GetUser, LogOutDataAccessBoundary {


    private final Map<String, User> accounts = new LinkedHashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;
        fetchAllUsers();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fetchAllUsers();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        timer.start();
    }

    @Override
    public void save(User user) throws IOException {
        accounts.put(user.getUsername(), user);
        MongoUploadUser mongoUploadUser = new MongoUploadUser();
        mongoUploadUser.uploadUsers(user.getUsername(), user.getUserid(), user.getPassword(), String.valueOf(user.getIsOnline()), user.getCreationTime().toString());
        fetchAllUsers();
    }

    public void fetchAllUsers() throws IOException {
        MongoDownloadUsers mongoDownloadUsers = new MongoDownloadUsers();
        List<JSONObject> Users = mongoDownloadUsers.downloadUsers();
        if (Users.isEmpty()) {
            return;
        }
        for (JSONObject user : Users) {
            String username = user.getString("username");
            String userid = user.getString("userid");
            String password = user.getString("password");
            String isOnline = user.getString("isOnline");
            String creationTimeText = user.getString("creation_time");
            LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
            User newUser = userFactory.create(username, userid, password, ldt);
            newUser.setOnline(Boolean.parseBoolean(isOnline));
            if (!accounts.containsKey(username)) {
                accounts.put(username, newUser);
            }
        }
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public String generateUserid() {
        return String.valueOf(accounts.size() + 1);
    }

    @Override
    public User getUser(String username) {
        return accounts.get(username);
    }

    public User getUserById(String userid) {
        for (User user : accounts.values()) {
            if (user.getUserid().equals(userid)) {
                return user;
            }
        }
        return null;
    }
    @Override
    public void logOut(String username){
        User userLoggingOut = accounts.get(username);
        userLoggingOut.setOnline(false);
        accounts.put(username, userLoggingOut);
    }
}
