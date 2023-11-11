package main.java.chattingSystem.frameworks_drivers.ui.views;

import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewManagerModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatRoomViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel chatRoomViews;
    private ChatRoomViewManagerModel chatRoomViewManagerModel;

    public ChatRoomViewManager(JPanel chatRoomViews, CardLayout cardLayout, ChatRoomViewManagerModel chatRoomViewManagerModel) {
        this.chatRoomViews = chatRoomViews;
        this.cardLayout = cardLayout;
        this.chatRoomViewManagerModel = chatRoomViewManagerModel;
        this.chatRoomViewManagerModel.addPropertyChangeListener(this);
    }
    public void addView(JPanel view, String viewnName) {
        chatRoomViews.add(view, viewnName);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(chatRoomViews, viewModelName);
        }
    }
}
