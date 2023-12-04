import chattingSystem.app.main;
import chattingSystem.entities.User.CommonUserFactory;
import chattingSystem.entities.User.User;
import chattingSystem.entities.User.UserFactory;
import chattingSystem.entities.Weather.Weather;
import chattingSystem.frameworks_drivers.data_access.FileUserDataAccessObject;
import chattingSystem.frameworks_drivers.ui.views.ChatRoomView;
import chattingSystem.frameworks_drivers.ui.views.GetWeatherView;
import chattingSystem.frameworks_drivers.ui.views.LoginView;
import chattingSystem.frameworks_drivers.ui.views.SignupView;
import chattingSystem.interface_adapter.state.ChatRoomState;
import chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import org.junit.Assert;
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

    private String location = "Toronto";

    private String location2 = "Changsha";

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

    public void disposeAll() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;

                app.dispose();
            }
        }
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


    public JButton getGetWeatherButton() {
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

        return cv.getWeather;
    }

    public JButton getJokeButton() {
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

        return cv.getJoke;
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


    public JTextField getWeatherText() {
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

        GetWeatherView getWeatherView = (GetWeatherView) cp;

        return getWeatherView.cityTextField;
    }

    public JButton getWeatherLocationButton() {
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

        GetWeatherView getWeatherView = (GetWeatherView) cp;

        return getWeatherView.getWeatherBotton;
    }

    public JTextArea getWeatherOutPut() {
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

        GetWeatherView getWeatherView = (GetWeatherView) cp;

        return getWeatherView.textDisplayLabel;
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

        User user = fileUserDataAccessObject.getUser(username);

        assertEquals(password, user.getPassword());

        disposeAll();
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

        disposeAll();
    }

//    @Test
//    public void testLoginDatabase() throws Exception {
//        main.main(null);
//        JButton login = getLoginButoon();
//        JTextField usernameField = getLoginUserNameField();
//        JTextField passwordField = getLoginPasswordField();
//
//        usernameField.setText(username.substring(0, username.length() - 1));
//        usernameField.dispatchEvent(new KeyEvent(usernameField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
//                0, KeyEvent.VK_UNDEFINED, username.charAt(username.length() - 1)));
//
//        passwordField.setText(password.substring(0, password.length() - 1));
//        passwordField.dispatchEvent(new KeyEvent(passwordField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
//                0, KeyEvent.VK_UNDEFINED, password.charAt(password.length() - 1)));
//
//        login.doClick();
//
//        FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject(new CommonUserFactory());
//
//        User user = fileUserDataAccessObject.getUser(username);
//
//        assertEquals(user.getUsername(), username);
//        assertEquals(user.getPassword(), password);
//
//        disposeAll();
//    }


    @Test
    public void testGetWeather() throws Exception {
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

        Thread.sleep(1000);

        JButton getWeather = getGetWeatherButton();

        assertDoesNotThrow(() -> getWeather.doClick());

        Thread.sleep(1000);

        JTextField locationField = getWeatherText();

        locationField.setText(location.substring(0, location.length() - 1));
        locationField.dispatchEvent(new KeyEvent(locationField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, location.charAt(location.length() - 1)));

        JButton getWeatherLocation = getWeatherLocationButton();

        assertDoesNotThrow(() -> getWeatherLocation.doClick());

        JTextArea text = getWeatherOutPut();

        String returnLocation = text.getText().substring(10, 10 + location.length());

        assertEquals(location, returnLocation);

        Thread.sleep(1000);

        locationField.setText(location2.substring(0, location2.length() - 1));
        locationField.dispatchEvent(new KeyEvent(locationField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, location2.charAt(location2.length() - 1)));

        assertDoesNotThrow(() -> getWeatherLocation.doClick());

        Thread.sleep(1000);

        JTextArea text2 = getWeatherOutPut();

        String returnLocation2 = text2.getText().substring(10, 10 + location2.length());

        assertEquals(location2, returnLocation2);

        disposeAll();
    }

    @Test
    public void testGetJoke() throws Exception {
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

        Thread.sleep(1000);

        JButton getJoke = getJokeButton();

        assertDoesNotThrow(() -> getJoke.doClick());

        Thread.sleep(1000);

        disposeAll();
    }


    @Test
    public void testSendMessage() throws IOException, InterruptedException {
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
        //wait for 1000ms
        Thread.sleep(1000);

        int length = testMessage.length();

        String outputMessage = getMessage();

        int outLength = outputMessage.length();
        assertEquals(username, outputMessage.substring(0, username.length()));
        assertEquals(testMessage, outputMessage.substring(outLength - length, outLength));

        disposeAll();
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

        disposeAll();
    }
}
