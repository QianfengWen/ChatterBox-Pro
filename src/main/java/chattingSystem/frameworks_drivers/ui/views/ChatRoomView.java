package main.java.chattingSystem.frameworks_drivers.ui.views;

import main.java.chattingSystem.interface_adapter.controllers.LogOutController;
import main.java.chattingSystem.interface_adapter.state.ChatRoomState;
import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewManagerModel;
import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import main.java.chattingSystem.interface_adapter.view_models.LoginViewModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName;
    private final JButton getWeather;
    private final JButton send;

    private final JButton logOut;
    public ChatRoomViewModel chatRoomViewModel;
    public LogOutController logOutController;

    private final ChatRoomViewManagerModel chatRoomViewManagerModel;

    public ChatRoomView(ChatRoomViewModel chatRoomViewModel,
                        ChatRoomViewManagerModel chatRoomViewManagerModel,
                        ChatRoomViewManager chatRoomViewManager,
                        LogOutController logOutController) {
        this.viewName = chatRoomViewModel.getViewName();
        this.chatRoomViewModel = chatRoomViewModel;
        this.chatRoomViewManagerModel = chatRoomViewManagerModel;
        this.logOutController = logOutController;
        chatRoomViewManager.addView(this, viewName);
        chatRoomViewModel.addPropertyChangeListener(this);

        this.setSize(720, 960); // Explicitly set the size of the frame
        // Initialize the view format, separate from other views
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;


        // Title
        JLabel title = new JLabel(chatRoomViewModel.TITLE_LABEL, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(30, 144, 255));
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        title.setOpaque(true);
        title.setBackground(new Color(220, 220, 220));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span across all columns
        gbc.insets = new Insets(20, 10, 10, 10);
        add(title, gbc);

        // Message Label - Positioned at the top left
        JLabel messageLabel = new JLabel(chatRoomViewModel.MESSAGE_LABEL);
        gbc.gridx = 0;
        gbc.gridy = 1; // Set to the row below the title
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Anchor to top-left
        gbc.insets = new Insets(10, 10, 5, 5);
        add(messageLabel, gbc);

        // Message Display
        JTextArea messageDisplay = new JTextArea();
        messageDisplay.setEditable(false);
        messageDisplay.setPreferredSize(new Dimension(400, 300)); // Adjusted for more height
        messageDisplay.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        messageDisplay.setLineWrap(true);
        messageDisplay.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageDisplay);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        gbc.gridx = 0;
        gbc.gridy = 2; // Adjusted to be below the messageLabel
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Take remaining horizontal space
        gbc.insets = new Insets(5, 5, 5, 10);
        add(scrollPane, gbc);

// Message Input
        JTextField messageInput = new JTextField();
        messageInput.setPreferredSize(new Dimension(400, 50));
        messageInput.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span across all columns
        gbc.insets = new Insets(5, 10, 5, 10);
        add(messageInput, gbc);

// Buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        this.getWeather = new JButton(ChatRoomViewModel.GET_WEATHER_BUTTON_LABEL);
        buttons.add(getWeather);
        this.send = new JButton(ChatRoomViewModel.SEND_MESSAGE_BUTTON_LABEL);
        buttons.add(send);
        this.logOut = new JButton(chatRoomViewModel.LOG_OUT_BUTTON_LABEL);
        buttons.add(logOut);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span across all columns
        gbc.insets = new Insets(5, 5, 20, 10);
        add(buttons, gbc);


        customizeComponents();
        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logOut)) {
                            ChatRoomState currentState = chatRoomViewModel.getState();
                            logOutController.execute(currentState.getUsername());
                        }
                    }
                }
        );

    }
    private void customizeComponents() {
        // customize components
        // more beautiful buttons in organized format
        send.setBackground(new Color(81, 204, 16));
        send.setForeground(Color.WHITE);
        send.setFocusPainted(false);
        send.setFont(new Font("Tahoma", Font.BOLD, 12));

        getWeather.setBackground(new Color(30, 144, 255));
        getWeather.setForeground(Color.WHITE);
        getWeather.setFocusPainted(false);
        getWeather.setFont(new Font("Tahoma", Font.BOLD, 12));

        logOut.setBackground(new Color(236, 180, 60, 255));
        logOut.setForeground(Color.WHITE);
        logOut.setFocusPainted(false);
        logOut.setFont(new Font("Tahoma", Font.BOLD, 12));



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
