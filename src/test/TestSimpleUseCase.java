import chattingSystem.app.main;
import chattingSystem.entities.User.CommonUserFactory;
import chattingSystem.entities.User.User;
import chattingSystem.entities.User.UserFactory;
import chattingSystem.frameworks_drivers.data_access.FileUserDataAccessObject;
import chattingSystem.frameworks_drivers.ui.views.ChatRoomView;
import chattingSystem.frameworks_drivers.ui.views.LoginView;
import chattingSystem.frameworks_drivers.ui.views.SignupView;
import chattingSystem.interface_adapter.state.ChatRoomState;
import chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestSimpleUseCase {
    private String username = "Test";
    private String password = "Qq123123";
    private String testMessage = "Test";

    public JButton getLoginButoon() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        LoginView lg = (LoginView) jp2.getComponent(1);

        JPanel buttons = (JPanel) lg.getComponent(5);

        return (JButton) buttons.getComponent(0);
    }

    public JButton getSignupButtonSign() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        SignupView lg = (SignupView) jp2.getComponent(0);

        JPanel buttons = (JPanel) lg.getComponent(8);

        return (JButton) buttons.getComponent(0);
    }

    public JButton getSignupButtonLogin() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        LoginView lg = (LoginView) jp2.getComponent(1);

        JPanel buttons = (JPanel) lg.getComponent(5);

        return (JButton) buttons.getComponent(1);
    }

    public JTextField getSignupUserNameField() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        SignupView sv = (SignupView) jp2.getComponent(0);

        return (JTextField) sv.getComponent(2);
    }

    public JTextField getSignupPasswordField() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        SignupView sv = (SignupView) jp2.getComponent(0);

        return (JTextField) sv.getComponent(4);
    }

    public JTextField getSignupRepeatPasswordField() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        SignupView sv = (SignupView) jp2.getComponent(0);

        return (JTextField) sv.getComponent(6);
    }

    public JTextField getLoginUserNameField() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        LoginView lg = (LoginView) jp2.getComponent(1);

        return (JTextField) lg.getComponent(2);
    }

    public JTextField getLoginPasswordField() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        LoginView lg = (LoginView) jp2.getComponent(1);

        return (JTextField) lg.getComponent(4);
    }

//    public ViewState getSignupState() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app); // found the window?
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        SignupView sv = (SignupView) jp2.getComponent(0);
//
//        return sv.signupViewModel.getState();
//    }

    public JButton getLogoutButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        ChatRoomView cv = (ChatRoomView) jp2.getComponent(0);

        return cv.logOut;
    }

    public JButton getSendButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        ChatRoomView cv = (ChatRoomView) jp2.getComponent(0);

        return cv.send;
    }

    public JTextField getSendField() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        ChatRoomView cv = (ChatRoomView) jp2.getComponent(0);

        return (JTextField) cv.getComponent(3);
    }

    public String getMessage() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        ChatRoomView cv = (ChatRoomView) jp2.getComponent(0);

        ChatRoomViewModel chatRoomViewModel = cv.chatRoomViewModel;

        ChatRoomState chatRoomState = chatRoomViewModel.getState();

        ArrayList<String> messages = new ArrayList<>(chatRoomState.getMessageHistory());

        return messages.get(messages.size() - 1);
    }

    public Component getFirstView() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        return jp2.getComponent(0);
    }


//    public JButton getWeatherButton() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app); // found the window?
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ChatRoomView cv = (ChatRoomView) jp2.getComponent(0);
//
//        return cv.getWeather;
//    }


    @Before
    public void init() throws IOException {
        UserFactory userFactory = new CommonUserFactory();
        FileUserDataAccessObject fileUserDataAccessObject;
        try {
            fileUserDataAccessObject = new FileUserDataAccessObject(userFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!fileUserDataAccessObject.existsByName(username)) {
            fileUserDataAccessObject.save(userFactory.create(username, "testID", password, LocalDateTime.now()));
        }
    }

    @Test
    public void testSignup() throws Exception {
        main.main(null);
        JButton signup = getSignupButtonLogin();
        signup.doClick();
        JTextField usernameField = getSignupUserNameField();
        JTextField passwordField = getSignupPasswordField();
        JTextField repeatPasswordField = getSignupRepeatPasswordField();
        JButton signupButtonSignupView = getSignupButtonSign();

        usernameField.setText(username.substring(0, username.length() - 1));
        usernameField.dispatchEvent(new KeyEvent(usernameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, username.charAt(username.length() - 1)));

        passwordField.setText(password.substring(0, password.length() - 1));
        passwordField.dispatchEvent(new KeyEvent(passwordField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, password.charAt(password.length() - 1)));

        repeatPasswordField.setText(password.substring(0, password.length() - 1));
        repeatPasswordField.dispatchEvent(new KeyEvent(repeatPasswordField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, password.charAt(password.length() - 1)));

        signupButtonSignupView.doClick();

        FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject(new CommonUserFactory());
        assertTrue(fileUserDataAccessObject.existsByName(username));
    }


    @Test
    public void testLogin() throws Exception {
        main.main(null);
        JButton login = getLoginButoon();
        JTextField usernameField = getLoginUserNameField();
        JTextField passwordField = getLoginPasswordField();

        usernameField.setText(username.substring(0, username.length() - 1));
        usernameField.dispatchEvent(new KeyEvent(usernameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, username.charAt(username.length() - 1)));

        passwordField.setText(password.substring(0, password.length() - 1));
        passwordField.dispatchEvent(new KeyEvent(passwordField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, password.charAt(password.length() - 1)));

        login.doClick();

        Component view = getFirstView();

        assertDoesNotThrow(() -> (ChatRoomView) view);
    }

    @Test
    public void testSendMessage() throws IOException {
        main.main(null);
        JButton login = getLoginButoon();
        JTextField usernameField = getLoginUserNameField();
        JTextField passwordField = getLoginPasswordField();

        usernameField.setText(username.substring(0, username.length() - 1));
        usernameField.dispatchEvent(new KeyEvent(usernameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, username.charAt(username.length() - 1)));

        passwordField.setText(password.substring(0, password.length() - 1));
        passwordField.dispatchEvent(new KeyEvent(passwordField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, password.charAt(password.length() - 1)));

        login.doClick();
        JButton send = getSendButton();
        JTextField sendField = getSendField();

        sendField.setText(testMessage.substring(1));
        sendField.dispatchEvent(new KeyEvent(sendField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, testMessage.charAt(0)));

        send.doClick();

        String outputMessage = getMessage();

        int length = testMessage.length();

        int outLength = outputMessage.length();

        assertEquals(testMessage, outputMessage.substring(outLength - length, outLength));
        assertEquals(username, outputMessage.substring(0, username.length()));
    }

    @Test
    public void testLogout() throws IOException {
        main.main(null);
        JButton login = getLoginButoon();
        JTextField usernameField = getLoginUserNameField();
        JTextField passwordField = getLoginPasswordField();

        usernameField.setText(username.substring(0, username.length() - 1));
        usernameField.dispatchEvent(new KeyEvent(usernameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, username.charAt(username.length() - 1)));

        passwordField.setText(password.substring(0, password.length() - 1));
        passwordField.dispatchEvent(new KeyEvent(passwordField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, password.charAt(password.length() - 1)));

        login.doClick();

        JButton logoutButton = getLogoutButton();

        logoutButton.doClick();

        FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject(new CommonUserFactory());

        User user = fileUserDataAccessObject.getUser(username);

        assertFalse(user.getIsOnline());
    }
}
