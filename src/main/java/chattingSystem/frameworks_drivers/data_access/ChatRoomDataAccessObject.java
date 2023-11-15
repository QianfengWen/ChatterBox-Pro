package main.java.chattingSystem.frameworks_drivers.data_access;


import main.java.chattingSystem.entities.ChatRoom.ChatRoom;
import main.java.chattingSystem.entities.ChatRoom.ChatRoomFactory;
import main.java.chattingSystem.entities.ChatRoom.CommonChatRoom;
import main.java.chattingSystem.entities.User.User;
import main.java.chattingSystem.entities.User.UserFactory;
import main.java.chattingSystem.use_cases.get_chat_room.GetChatRoomDataAccessBoundary;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomDataAccessBoundary;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ChatRoomDataAccessObject implements  GetChatRoomDataAccessBoundary, JoinChatRoomDataAccessBoundary{
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private FileUserDataAccessObject fileUserDataAccessObject;

    private final File csvFile;
    private ChatRoomFactory chatRoomFactory;


    public ChatRoomDataAccessObject(String csvPath, ChatRoomFactory chatRoomFactory, UserFactory userFactory, FileUserDataAccessObject fileUserDataAccessObject) throws FileNotFoundException {
        csvFile = new File(csvPath);
        this.chatRoomFactory = chatRoomFactory;
        this.fileUserDataAccessObject = fileUserDataAccessObject;

        headers.put("ChatRoomId", 0);
        headers.put("MembersId", 1);
        headers.put("MessagesId", 2);
        headers.put("creation_time", 3);

        //if the file is empty, create a default chatroom
        if (csvFile.length() == 0) {
            String chatRoomId = "0";
            List<User> InitialMembers = new ArrayList<>();
            User admin = userFactory.create("admin", "0", "Admin123", LocalDateTime.now());
            InitialMembers.add(admin);
            String InitialMessages = "Here starts the chat history.\n";
            LocalDateTime creationTime = LocalDateTime.now();
            ChatRoom chatRoom = chatRoomFactory.create(chatRoomId, InitialMembers, InitialMessages, creationTime);
            chatRooms.put(chatRoomId, chatRoom);
            this.update();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("ChatRoomId, MembersId, MessagesId, creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String chatRoomId = String.valueOf(col[headers.get("ChatRoomId")]);
                    String userid = String.valueOf(col[headers.get("MembersId")]);
                    String password = String.valueOf(col[headers.get("MessagesId")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    List<User> Members = new ArrayList<>();
                    for (String s : userid.split(" ")) {
                        User user = fileUserDataAccessObject.getUserById(s);
                        Members.add(user);
                    }
                    ChatRoom chatRoom = chatRoomFactory.create(chatRoomId, Members, password, ldt);
                    chatRooms.put(chatRoomId, chatRoom);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean existsById(String id) {
        return chatRooms.containsKey(id);
    }

    @Override
    public void joinChatRoom(User user) {
        ChatRoom chatRoom = chatRooms.get("0");
        chatRoom.addMember(user);
        this.update();
    }

    private void update() {
        // update the csv file
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (ChatRoom chatRoom :  chatRooms.values()) {
                String line = String.format("%s,%s,%s,%s",
                        chatRoom.getChatRoomID(),
                        chatRoom.getMembersIdString(),
                        chatRoom.getChatHistoryString(),
                        chatRoom.getCreationTime());
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ChatRoom getChatRoom(String id) {
        return chatRooms.get(id);
    }


}
