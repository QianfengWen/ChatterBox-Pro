package main.java.chattingSystem.frameworks_drivers.data_access;

import main.java.chattingSystem.entities.Message.Message;
import main.java.chattingSystem.entities.Message.MessageFactory;
import main.java.chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChatroomUserDataAccessObject implements SendMessageUserDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<Integer, Message> history = new HashMap<>();
    private final Map<String, String> userhistory = new HashMap<>();
    private MessageFactory messageFactory;

    public ChatroomUserDataAccessObject(String csvPath, MessageFactory messageFactory) throws IOException {
        this.messageFactory = messageFactory;
        csvFile = new File(csvPath);
        headers.put("messageID", 0);
        headers.put("senderID", 1);
        headers.put("senderName", 2);
        headers.put("text", 3);
        headers.put("timestamp", 4);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("messageID, senderID, senderName, text, timestamp");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    int messageID = Integer.parseInt(col[headers.get("messageID")]);
                    String senderID = String.valueOf(col[headers.get("senderID")]);
                    String senderName = String.valueOf(col[headers.get("senderName")]);
                    String timestamp = String.valueOf(col[headers.get("timestamp")]);
                    String text = String.valueOf(col[headers.get("text")]);
                    LocalDateTime ldt = LocalDateTime.parse(timestamp);
                    Message message = messageFactory.create(messageID, senderID, senderName, ldt, text);
                    userhistory.put(senderName, text + "\n");
                    history.put(messageID, message);
                }
            }
        }
    }

    @Override
    public String get(String username) {
        return userhistory.get(username);
    }

    @Override
    public void save(Message message) {
        history.put(message.getMessageID(), message);
        this.save();
    }

    @Override
    public int generateMessageid() {
        return history.size() + 1;
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Message message : history.values()) {
                String line = String.format("%s,%s,%s,%s,%s",
                        message.getMessageID(), message.getSenderID(), message.getSenderName(),
                        message.getText(), message.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
