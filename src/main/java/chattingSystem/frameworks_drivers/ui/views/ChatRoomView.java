package chattingSystem.frameworks_drivers.ui.views;

import chattingSystem.interface_adapter.controllers.LogOutController;
import chattingSystem.interface_adapter.controllers.RefreshingMessageController;
import chattingSystem.interface_adapter.controllers.SendMessageController;
import chattingSystem.interface_adapter.controllers.ShowWeatherController;
import chattingSystem.interface_adapter.state.ChatRoomState;
import chattingSystem.interface_adapter.view_models.ChatRoomViewManagerModel;
import chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import chattingSystem.interface_adapter.view_models.LoginViewModel;
import chattingSystem.interface_adapter.view_models.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ChatRoomView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName;
    private final JButton getWeather;
    private final JButton send;

    private final JButton logOut;
    public ChatRoomViewModel chatRoomViewModel;
    public LogOutController logOutController;
    public ShowWeatherController showWeatherController;

    public SendMessageController sendMessageController;
    public RefreshingMessageController refreshingMessageController;


    private final ChatRoomViewManagerModel chatRoomViewManagerModel;

    public ChatRoomView(ChatRoomViewModel chatRoomViewModel,
                        ChatRoomViewManagerModel chatRoomViewManagerModel,
                        ChatRoomViewManager chatRoomViewManager,
                        LogOutController logOutController,
                        ShowWeatherController showWeatherController,
                        SendMessageController sendMessageController,
                        RefreshingMessageController refreshingMessageController)    {
        this.viewName = chatRoomViewModel.getViewName();
        this.chatRoomViewModel = chatRoomViewModel;
        this.chatRoomViewManagerModel = chatRoomViewManagerModel;
        this.logOutController = logOutController;
        this.showWeatherController = showWeatherController;
        this.sendMessageController = sendMessageController;
        this.refreshingMessageController = refreshingMessageController;
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
        JTextArea messageDisplay = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(13, 10, 400, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messageDisplay.setEditable(false);
        messageDisplay.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        messageDisplay.setLineWrap(true);
        messageDisplay.setWrapStyleWord(true);
        gbc.gridx = 0;
        gbc.gridy = 2; // Adjusted to be below the messageLabel
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Take remaining horizontal space
        gbc.insets = new Insets(5, 5, 5, 10);
        scrollPane.setViewportView(messageDisplay);
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
        // if the user clicks the close button on the frame, the user will autonatically log out
        chatRoomViewModel.getCurrentframe().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ChatRoomState currentState = chatRoomViewModel.getState();
                int JOP = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log out", JOptionPane.YES_NO_OPTION);
                if (JOP == JOptionPane.YES_OPTION){
                    logOutController.execute(currentState.getUsername());
                    JFrame currentFrame = chatRoomViewModel.getCurrentframe();
                    currentFrame.dispose();
                }
            }
        });
        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logOut)) {
                            ChatRoomState currentState = chatRoomViewModel.getState();
                            int JOP = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log out", JOptionPane.YES_NO_OPTION);
                            if (JOP == JOptionPane.YES_OPTION){
                                logOutController.execute(currentState.getUsername());
                                JFrame currentFrame = chatRoomViewModel.getCurrentframe();
                                currentFrame.dispose();
                            }

                        }
                    }
                }
        );
        getWeather.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(getWeather)) {
                            try {
                                showWeatherController.execute();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
        );

        send.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(send)) {
                            ChatRoomState currentState = chatRoomViewModel.getState();
                            String currentMessage = messageInput.getText();
                            if (!Objects.equals(currentMessage, "")) {
                                LocalDateTime timestamp = LocalDateTime.now();
                                try {
                                    sendMessageController.execute(currentState.getUsername(), currentState.getSenderId(), currentMessage, timestamp, currentState.getChatRoomId());
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
//                                List<String> messages = currentState.getMessageHistory();
//                                messageDisplay.setText(null);
//                                for (String message : messages) {
//                                    messageDisplay.append(message + "\n");
//                                }
                                messageInput.setText("");
                            } else {
                                JOptionPane.showMessageDialog(ChatRoomView.this, "The message is empty.");
                            }
                        }
                    }
                }
        );

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentRows = messageDisplay.getLineCount();
//                System.out.println(currentRows);
                refreshingMessageController.execute();
                ChatRoomState currentState = chatRoomViewModel.getState();
                List<String> messages = currentState.getMessageHistory();
//                System.out.println(messages.size() + 1);
                if (currentRows != messages.size() + 1) {
                    messageDisplay.setText(null);
                    for (String message : messages) {
                        messageDisplay.append(message + "\n");
                    }
                }
            }
        });
        timer.start();




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
