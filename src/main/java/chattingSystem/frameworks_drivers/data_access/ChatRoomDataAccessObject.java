package chattingSystem.frameworks_drivers.data_access;



import chattingSystem.entities.ChatRoom.ChatRoom;
import chattingSystem.entities.ChatRoom.ChatRoomFactory;
import chattingSystem.entities.Message.Message;
import chattingSystem.entities.Message.MessageFactory;
import chattingSystem.entities.User.User;
import chattingSystem.entities.User.UserFactory;
import chattingSystem.frameworks_drivers.api.MongoDownloadChatRoom;
import chattingSystem.frameworks_drivers.api.MongoDownloadMessage;
import chattingSystem.frameworks_drivers.api.MongoUpdateChatRoom;
import chattingSystem.frameworks_drivers.api.MongoUploadMessage;
import chattingSystem.interface_adapter.state.ChatRoomState;
import chattingSystem.use_cases.get_chat_room.GetChatRoomDataAccessBoundary;
import chattingSystem.use_cases.join_chat_room.JoinChatRoomDataAccessBoundary;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesDataAccessBoundary;
import chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;
import org.json.JSONObject;


import java.io.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

public class ChatRoomDataAccessObject implements  GetChatRoomDataAccessBoundary, JoinChatRoomDataAccessBoundary, SendMessageUserDataAccessInterface, RefreshMessagesDataAccessBoundary {
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();
    private final Map<String, Message> messages = new HashMap<>();

    private FileUserDataAccessObject fileUserDataAccessObject;

    private ChatRoomFactory chatRoomFactory;

    private MessageFactory messageFactory;


    public ChatRoomDataAccessObject(ChatRoomFactory chatRoomFactory, UserFactory userFactory, MessageFactory messageFactory, FileUserDataAccessObject fileUserDataAccessObject) throws IOException {
//        csvFile = new File(csvPath);
        this.chatRoomFactory = chatRoomFactory;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.messageFactory = messageFactory;
        fetchChatRoom();
        // constant time to fetch all the chat rooms
        fetchMessages();

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
        for (String s : membersId.split(" ")) {
            User user = fileUserDataAccessObject.getUserById(s);
            if (chatRooms.get("1") == null) {
                List<User> InitialMembers = new ArrayList<>();
                InitialMembers.add(user);
                LocalDateTime creationTime = LocalDateTime.now();
                ChatRoom firstChatRoom = chatRoomFactory.create("1", InitialMembers, creationTime);
                chatRooms.put("1", firstChatRoom);
            }
            if (chatRooms.get("1").getMembers().contains(user)) {
                continue;
            }
            chatRooms.get("1").addMember(user);
        }
    }

    public void fetchMessages() throws IOException {
        MongoDownloadMessage mongoDownloadMessage = new MongoDownloadMessage();
        List<JSONObject> messagesJson = mongoDownloadMessage.downloadMessage();
        if (messagesJson.isEmpty()) {
            return;
        }
        for (JSONObject message : messagesJson) {
            String messageId = message.getString("messageid");
            String username = message.getString("username");
            String userid = message.getString("userid");
            String currentMessage = message.getString("message");
            String creationTime = message.getString("creation_time");
            LocalDateTime ldt = LocalDateTime.parse(creationTime);
            Message newMessage = messageFactory.create(messageId, userid, username, ldt, currentMessage);
            if (this.messages.containsKey(messageId)) {
                continue;
            }
            this.messages.put(messageId, newMessage);
        }
    }

    private void update(String chatRoomId, String membersId) throws IOException {
        MongoUpdateChatRoom mongoUpdateChatRoom = new MongoUpdateChatRoom();
        mongoUpdateChatRoom.updateChatRoomsMembers(chatRoomId, membersId);
    }


    @Override
    public ChatRoom getChatRoom(String id) {
        return chatRooms.get(id);
    }

    @Override
    public String getUsername(String username) {
        return username;
    }

    @Override
    public void save(String id, Message message) throws IOException {
        messages.put(id, message);
        MongoUploadMessage mongoUploadMessage = new MongoUploadMessage();
        mongoUploadMessage.uploadMessage(message.getMessageID(), message.getSenderName(), message.getSenderID(), message.getText(), message.getCreationTime().toString());
        fetchMessages();
    }


    @Override
    public String generateMessageid(String id) {
        return String.valueOf(messages.size() + 1);
    }

    @Override
    public List<String> fetchAllMessages() {
        try {
            fetchMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> messagesList = new ArrayList<>();
        for (Message message : messages.values()) {
            String messageString = message.getSenderName()
                    + "(ID: "+ message.getSenderID() + ")" + " at " +
                    message.getCreationTime().getYear() + "-" + message.getCreationTime().getMonthValue() + "-" + message.getCreationTime().getDayOfMonth() + "-" + message.getCreationTime().getHour() + ":" + message.getCreationTime().getMinute() + ":" + message.getCreationTime().getSecond() +
                    ": " + message.getText();
            messagesList.add(messageString);
        }
        return messagesList;
    }
}
