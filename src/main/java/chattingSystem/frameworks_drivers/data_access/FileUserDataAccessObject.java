package chattingSystem.frameworks_drivers.data_access;

import chattingSystem.entities.User.User;
import chattingSystem.entities.User.UserFactory;
import chattingSystem.frameworks_drivers.api.MongoDownloadUsers;
import chattingSystem.frameworks_drivers.api.MongoUploadUser;
import chattingSystem.use_cases.get_chat_room.GetUser;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.login.LoginUserDataAccessInterface;
import chattingSystem.use_cases.signup.SignupUserDataAccessInterface;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, GetUser, LogOutDataAccessBoundary {


    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;
        fetchAllUsers();

//        csvFile = new File(csvPath);
//        headers.put("username", 0);
//        headers.put("userid", 1);
//        headers.put("password", 2);
//        headers.put("creation_time", 3);
//
//        if (csvFile.length() == 0) {
////            save();
//        } else {
//
//            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
//                String header = reader.readLine();
//
//                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
//                assert header.equals("username, userid, password,creation_time");
//
//                String row;
//                while ((row = reader.readLine()) != null) {
//                    String[] col = row.split(",");
//                    String username = String.valueOf(col[headers.get("username")]);
//                    String userid = String.valueOf(col[headers.get("userid")]);
//                    String password = String.valueOf(col[headers.get("password")]);
//                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
//                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
//                    User user = userFactory.create(username, userid, password, ldt);
//                    accounts.put(username, user);
//                }
//            }
//        }
    }

    @Override
    public void save(User user) throws IOException {
        accounts.put(user.getUsername(), user);
        MongoUploadUser mongoUploadUser = new MongoUploadUser();
        mongoUploadUser.uploadUsers(user.getUsername(), user.getUserid(), user.getPassword(), user.getCreationTime().toString());
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
            String creationTimeText = user.getString("creation_time");
            LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
            User newUser = userFactory.create(username, userid, password, ldt);
            if (!accounts.containsKey(username)) {
                accounts.put(username, newUser);
            }
        }
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

//    private void save() {
////        BufferedWriter writer;
//        try {
////            writer = new BufferedWriter(new FileWriter(csvFile));
////            writer.write(String.join(",", headers.keySet()));
////            writer.newLine();
////
////            for (User user : accounts.values()) {
////                String line = String.format("%s,%s,%s,%s",
////                        user.getUsername(), user.getUserid(), user.getPassword(), user.getCreationTime());
////                writer.write(line);
////                writer.newLine();
////            }
////
////            writer.close();
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


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

//    @Override
//    public void deleteAllUsers() {
//        accounts.clear();
//        this.save();
//    }

//    @Override
//    public Map<String, User> getAccounts() {
//        return accounts;
//    }
}
