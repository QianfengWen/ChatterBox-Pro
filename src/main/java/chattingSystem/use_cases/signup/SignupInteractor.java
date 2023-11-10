package main.java.chattingSystem.use_cases.signup;



import main.java.chattingSystem.entities.User.User;
import main.java.chattingSystem.entities.User.UserFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareNameFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareRepeatPWFailView("Passwords don't match.");
        } else if (!signupInputData.isValidPassword()) {
            userPresenter.prepareInvalidPWFailView("Password is not valid. It must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one number.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            String id = userDataAccessObject.generateUserid();
            User user = userFactory.create(signupInputData.getUsername(), id, signupInputData.getPassword(), now);
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
