package main.java.chattingSystem.frameworks_drivers.ui.views;

import main.java.chattingSystem.interface_adapter.controllers.JoinChatRoomController;
import main.java.chattingSystem.interface_adapter.controllers.LoginController;
import main.java.chattingSystem.interface_adapter.state.LoginState;
import main.java.chattingSystem.interface_adapter.view_models.LoginViewModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private int loginSuccess = 0;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final LoginController loginController;
    private final JoinChatRoomController joinChatRoomController;

    private final JButton logIn;
    private final JButton signUp;
    private final JButton cancel;

    public LoginView(LoginController controller, JoinChatRoomController joinChatRoomController,LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.loginController = controller;
        this.joinChatRoomController= joinChatRoomController;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        loginViewModel.addPropertyChangeListener(this);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel(loginViewModel.TITLE_LABEL, SwingConstants.CENTER);
        title.setFont(new Font("Times", Font.BOLD, 24));
        // let title be more beautiful and higher in the window
        title.setForeground(new Color(30, 144, 255));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(title, gbc);

        // Username label and text field
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        add(new JLabel(loginViewModel.USERNAME_LABEL), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 5, 10);
        add(usernameInputField, gbc);

        // Password label and text field
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 10, 5, 5);
        add(new JLabel(loginViewModel.PASSWORD_LABEL), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 5, 10);
        add(passwordInputField, gbc);


        // Buttons
        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        logIn = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        buttonsPanel.add(logIn);
        signUp = new JButton("Sign up");
        buttonsPanel.add(signUp);
        add(buttonsPanel, gbc);
        cancel = new JButton(LoginViewModel.CANCEL_BUTTON_LABEL);
        buttonsPanel.add(cancel);
        add(buttonsPanel, gbc);

        // Customize components
        customizeComponents();

        // Add listeners
        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            if (e.getSource().equals(cancel)) {
                                // pump a message to the user whether to confirm the cancelation
                                int result = JOptionPane.showConfirmDialog(LoginView.this, "Are you sure you want to cancel the login process?");
                                if (result != JOptionPane.YES_OPTION) {
                                    return;
                                }
                                // Clear the input fields
                                usernameInputField.setText("");
                                passwordInputField.setText("");

                                // Reset the state if needed
                                LoginState currentState = loginViewModel.getState();
                                currentState.setUsername("");
                                currentState.setPassword("");
                                loginViewModel.setState(currentState);

                                // Optionally, inform the user that the signup process has been canceled
                                JOptionPane.showMessageDialog(LoginView.this, "Login process has been canceled.");

                                // Handle any other logic that should occur after canceling the login,
                                // such as switching to a different view, if applicable.
                                // ...
                            }
                        }

                    }
                }
        );
        signUp.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(signUp)) {
                            viewManagerModel.setActiveView("sign up");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
    }
    private void customizeComponents() {
        // Customize the UI elements here (fonts, borders, colors, etc.)
        // Consider using a UIManager to set a theme or custom styling

        // making the window bigger only for the login view

        // making the caption more beautiful
        usernameInputField.setFont(new Font("Times", Font.BOLD, 12));
        passwordInputField.setFont(new Font("Times", Font.BOLD, 12));

        // setting the background color
        usernameInputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        passwordInputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        logIn.setBackground(new Color(7, 129, 23, 205));
        logIn.setForeground(Color.WHITE);
        logIn.setFocusPainted(false);
        logIn.setFont(new Font("Arial", Font.BOLD, 12));

        signUp.setBackground(new Color(30, 144, 255));
        signUp.setForeground(Color.WHITE);
        signUp.setFocusPainted(false);
        signUp.setFont(new Font("Arial", Font.BOLD, 12));

        cancel.setBackground(Color.LIGHT_GRAY);
        cancel.setForeground(Color.WHITE);
        cancel.setFocusPainted(false);
        cancel.setFont(new Font("Arial", Font.BOLD, 12));
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            LoginState state = (LoginState) evt.getNewValue();
            setFields(state);
            if (state.getUsernameError() != null) {
                JOptionPane.showMessageDialog(this, state.getUsernameError());
                state.setUsernameError(null);
            } else if (state.getPasswordError() != null) {
                JOptionPane.showMessageDialog(this, state.getPasswordError());
                state.setPasswordError(null);
            }
            else{
                    LoginState currentState = loginViewModel.getState();
                        try {
                            joinChatRoomController.execute(currentState.getUsername());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        JOptionPane.showMessageDialog(this, "Login successful!");
                        loginSuccess++;
                        // Clear the input fields
                        usernameInputField.setText("");
                        passwordInputField.setText("");
                        // Reset the state if needed
                        currentState.setUsername("");
                        currentState.setPassword("");
                        loginViewModel.setState(currentState);
                        viewManagerModel.setActiveView("logged in");
            }

    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}
