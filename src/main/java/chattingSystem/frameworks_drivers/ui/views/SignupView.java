package main.java.chattingSystem.frameworks_drivers.ui.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.java.chattingSystem.interface_adapter.controllers.CreateChatRoomController;
import main.java.chattingSystem.interface_adapter.view_models.SignupViewModel;
import main.java.chattingSystem.interface_adapter.controllers.SignupController;
import main.java.chattingSystem.interface_adapter.state.SignupState;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;
    private final CreateChatRoomController createChatRoomController;
    private final JButton signUp;
    private final JButton cancel;

    private final JLabel passwordHintLabel = new JLabel("<html>Password must be at least 8 characters long and include:<ul>" +
            "<li>At least one uppercase letter</li>" +
            "<li>At least one lowercase letter</li>" +
            "<li>At least one number</li></ul></html>");

    public SignupView(SignupController controller, CreateChatRoomController createChatRoomController, SignupViewModel signupViewModel, ViewManagerModel viewManagerModel) {
        this.signupController = controller;
        this.createChatRoomController = createChatRoomController;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
        signupViewModel.addPropertyChangeListener(this);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL, SwingConstants.CENTER);
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
        add(new JLabel(SignupViewModel.USERNAME_LABEL), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 5, 10);
        add(usernameInputField, gbc);

        // Password label and text field
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 10, 5, 5);
        add(new JLabel(SignupViewModel.PASSWORD_LABEL), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 5, 10);
        add(passwordInputField, gbc);

        // Repeat Password label and text field
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 10, 5, 5);
        add(new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 5, 5, 10);
        add(repeatPasswordInputField, gbc);

        // Password hint label
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 5, 10);
        // the password hint label is initially hidden, when typing in the password field it will be shown
        passwordHintLabel.setVisible(false);
        add(passwordHintLabel, gbc);

        // Buttons
        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttonsPanel.add(signUp);
        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        buttonsPanel.add(cancel);
        add(buttonsPanel, gbc);

        // Customize components
        customizeComponents();

        // Adding action listeners
        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(signUp)) {
                            SignupState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(cancel)) {
                    // pump a message to the user whether to confirm the cancelation
                    int result = JOptionPane.showConfirmDialog(SignupView.this, "Are you sure you want to cancel the signup process?");
                    if (result != JOptionPane.YES_OPTION) {
                        return;
                    }
                    // Clear the input fields
                    usernameInputField.setText("");
                    passwordInputField.setText("");
                    repeatPasswordInputField.setText("");

                    // Reset the state if needed
                    SignupState currentState = signupViewModel.getState();
                    currentState.setUsername("");
                    currentState.setPassword("");
                    currentState.setRepeatPassword("");
                    signupViewModel.setState(currentState);

                    // Optionally, inform the user that the signup process has been canceled
                    JOptionPane.showMessageDialog(SignupView.this, "Signup process has been canceled.");


                    // Handle any other logic that should occur after canceling the signup,
                    // such as switching to a different view, if applicable.
                    // ...
                    viewManagerModel.setActiveView("log in");

                    viewManagerModel.firePropertyChanged();
                }
            }
        });



        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
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
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                        int passwordLength = passwordInputField.getPassword().length;
                        passwordHintLabel.setVisible(passwordLength > 0);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState); // Hmm, is this necessary?
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

    }

    private void customizeComponents() {
        // Customize the UI elements here (fonts, borders, colors, etc.)
        // Consider using a UIManager to set a theme or custom styling
        // ...

        // making the caption more beautiful
        usernameInputField.setFont(new Font("Times", Font.BOLD, 12));
        passwordInputField.setFont(new Font("Times", Font.BOLD, 12));
        repeatPasswordInputField.setFont(new Font("Times", Font.BOLD, 12));

        // setting the background color
        usernameInputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        passwordInputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        repeatPasswordInputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

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
        // This method is called whenever the timer fires an action event.
        // We don't need to do anything here, but we need to implement this method
        // because we're implementing the ActionListener interface.
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            SignupState state = (SignupState) evt.getNewValue();
            if (state.getUsernameError() != null) {
                JOptionPane.showMessageDialog(this, state.getUsernameError());
                state.setUsernameError(null);
            }else if (state.getPasswordError() != null) {
                JOptionPane.showMessageDialog(this, state.getPasswordError());
                state.setPasswordError(null);
            }else if (state.getRepeatPasswordError() != null) {
                JOptionPane.showMessageDialog(this, state.getRepeatPasswordError());
                state.setRepeatPasswordError(null);
            }else{
                if (viewManagerModel.getActiveView().equals("sign up")) {
                    JOptionPane.showMessageDialog(this, "Signup successful!");
                }
            }

    }
}
