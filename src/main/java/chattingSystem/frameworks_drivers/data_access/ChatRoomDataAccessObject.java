package chattingSystem.frameworks_drivers.data_access;


import chattingSystem.entities.ChatRoom.ChatRoom;
import chattingSystem.entities.ChatRoom.ChatRoomFactory;
import chattingSystem.entities.ChatRoom.CommonChatRoom;
import chattingSystem.entities.User.User;
import chattingSystem.entities.User.UserFactory;
import chattingSystem.frameworks_drivers.api.MongoDownloadChatRoom;
import chattingSystem.frameworks_drivers.api.MongoUpdateChatRoom;
import chattingSystem.use_cases.get_chat_room.GetChatRoomDataAccessBoundary;
import chattingSystem.use_cases.join_chat_room.JoinChatRoomDataAccessBoundary;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ChatRoomDataAccessObject implements  GetChatRoomDataAccessBoundary, JoinChatRoomDataAccessBoundary{
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();

    private FileUserDataAccessObject fileUserDataAccessObject;

    private ChatRoomFactory chatRoomFactory;


    public ChatRoomDataAccessObject(ChatRoomFactory chatRoomFactory, UserFactory userFactory, FileUserDataAccessObject fileUserDataAccessObject) throws IOException {
//        csvFile = new File(csvPath);
        this.chatRoomFactory = chatRoomFactory;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        fetchChatRoom();
    }

    @Override
    public boolean existsById(String id) {
        return chatRooms.containsKey(id);
    }

    @Override
    public void joinChatRoom(User user) throws IOException {
        ChatRoom chatRoom = chatRooms.get("1");
        chatRoom.addMember(user);
        update("1", chatRoom.getMembersIdString());
        fetchChatRoom();
    }

    public void fetchChatRoom() throws IOException {
        MongoDownloadChatRoom mongoDownloadChatRooms = new MongoDownloadChatRoom();
        JSONObject ChatRoom = mongoDownloadChatRooms.downloadChatRoom("1");
        if (ChatRoom.isEmpty()) {
            return;
        }
        String membersId = ChatRoom.getString("MembersId");
        String chatHistory = ChatRoom.getString("Messages");
        for (String s : membersId.split(" ")) {
            User user = fileUserDataAccessObject.getUserById(s);
            if (chatRooms.get("1") == null) {
                List<User> InitialMembers = new ArrayList<>();
                InitialMembers.add(user);
                LocalDateTime creationTime = LocalDateTime.now();
                ChatRoom firstChatRoom = chatRoomFactory.create("1", InitialMembers, chatHistory, creationTime);
                chatRooms.put("1", firstChatRoom);
            }
            if (chatRooms.get("1").getMembers().contains(user)) {
                continue;
            }
            chatRooms.get("1").addMember(user);
        }
    }

    private void update(String chatRoomId, String membersId) throws IOException {
        MongoUpdateChatRoom mongoUpdateChatRoom = new MongoUpdateChatRoom();
        mongoUpdateChatRoom.updateChatRoomsMembers(chatRoomId, membersId);

        // update the csv file

//        BufferedWriter writer;
//        try {
//            writer = new BufferedWriter(new FileWriter(csvFile));
//            writer.write(String.join(",", headers.keySet()));
//            writer.newLine();
//
//            for (ChatRoom chatRoom :  chatRooms.values()) {
//                String line = String.format("%s,%s,%s,%s",
//                        chatRoom.getChatRoomID(),
//                        chatRoom.getMembersIdString(),
//                        chatRoom.getChatHistoryString(),
//                        chatRoom.getCreationTime());
//                writer.write(line);
//                writer.newLine();
//            }
//            writer.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }


    @Override
    public ChatRoom getChatRoom(String id) {
        return chatRooms.get(id);
    }


}
