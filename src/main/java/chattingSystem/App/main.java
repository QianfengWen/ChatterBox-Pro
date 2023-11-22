package chattingSystem.App;

import chattingSystem.entities.ChatRoom.CommonChatRoomFactory;
import chattingSystem.entities.User.CommonUserFactory;
import chattingSystem.frameworks_drivers.data_access.ChatRoomDataAccessObject;
import chattingSystem.frameworks_drivers.data_access.FileUserDataAccessObject;
import chattingSystem.frameworks_drivers.ui.views.*;
import chattingSystem.interface_adapter.view_models.*;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class main {
    public static void main(String[] args) throws IOException {
        // initialize MongoDB

        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.
        // The log in and sign up window.
        JFrame application = new JFrame("Application");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);
        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        FileUserDataAccessObject userDataAccessObject;


        try {
            userDataAccessObject = new FileUserDataAccessObject(new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ChatRoomDataAccessObject chatRoomDataAccessObject = new ChatRoomDataAccessObject(
                new CommonChatRoomFactory(),
                new CommonUserFactory(),
                userDataAccessObject);


        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel,
                signupViewModel,
                loginViewModel,
                userDataAccessObject
                );
        assert signupView != null;

        views.add(signupView, signupView.viewName);


        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel,
                viewManager,
                loginViewModel,
                userDataAccessObject,
                chatRoomDataAccessObject,
                chatRoomDataAccessObject,
                userDataAccessObject,
                userDataAccessObject
                );
        assert loginView != null;
        views.add(loginView, loginView.viewName);
        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setPreferredSize(new Dimension(480, 360));
        application.pack();
        application.setVisible(true);
    }
}
