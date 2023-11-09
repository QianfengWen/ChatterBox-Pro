package main.java.chattingSystem.App;

import main.java.chattingSystem.entities.User.CommonUserFactory;
import main.java.chattingSystem.frameworks_drivers.data_access.FileUserDataAccessObject;
import main.java.chattingSystem.frameworks_drivers.ui.views.SignupView;
import main.java.chattingSystem.frameworks_drivers.ui.views.ViewManager;
import main.java.chattingSystem.interface_adapter.view_models.SignupViewModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Signup");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        SignupViewModel signupViewModel = new SignupViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, userDataAccessObject);
        assert signupView != null;
        views.add(signupView, signupView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
