package main.java.chattingSystem.interface_adapter.view_models;

import main.java.chattingSystem.entities.ChatRoom.ChatRoom;
import main.java.chattingSystem.interface_adapter.state.ChatRoomState;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ChatRoomViewModel extends ViewModel{

    private JFrame currentframe;
    public String TITLE_LABEL = "ChatRoom";
    public static final String SEND_MESSAGE_BUTTON_LABEL = "Send";

    public static final String GET_WEATHER_BUTTON_LABEL = "Get Weather";

    public String MESSAGE_LABEL = "Message";
    public String CHATROOM_ID_LABEL = "";
    public String USER_NAME_LABEL = "";
    public String messageDisplay = "All messages will be displayed here.";

    public String LOG_OUT_BUTTON_LABEL = "Log out";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private ChatRoomState state = new ChatRoomState();

    public ChatRoomViewModel() {
        super("chat room");
    }
    public JFrame getCurrentframe() {
        return currentframe;
    }
    public void setCurrentframe(JFrame currentframe) {
        this.currentframe = currentframe;
    }

    public ChatRoomState getState() {return state;}

    public void setState(ChatRoomState chatRoomState) {
        this.state = chatRoomState;
    }

    public void setChatRoomIdLabel(String chatRoomIdLabel) {
        this.CHATROOM_ID_LABEL = chatRoomIdLabel;
    }

    public void setUserNameLabel(String userNameLabel) {
        this.USER_NAME_LABEL = userNameLabel;
        this.TITLE_LABEL = "ChatRoom - " + "Current User: " + userNameLabel;
    }
    @Override
    public String getViewName() {
        return USER_NAME_LABEL;
    }


    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
