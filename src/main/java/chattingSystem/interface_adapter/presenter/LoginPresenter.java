package chattingSystem.interface_adapter.presenter;

import chattingSystem.frameworks_drivers.ui.views.ViewManager;
import chattingSystem.interface_adapter.state.LoginState;
import chattingSystem.interface_adapter.view_models.LoginViewModel;
import chattingSystem.interface_adapter.view_models.ViewManagerModel;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.login.LoginOutputData;
import chattingSystem.use_cases.login.LoginOutputBoundary;

public class LoginPresenter implements LoginOutputBoundary{
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ViewManager viewManager;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel,
                          ViewManager viewManager) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response, LogOutDataAccessBoundary logOutDataAccessBoundary) {
        // TODO replace the logic to load chatroom and create a new chatroom view for current user
        if (viewManagerModel.getActiveView().equals("log in")) {
            LoginState loginState = loginViewModel.getState();
            loginState.setLoginSuccess(true);
            loginViewModel.firePropertyChanged();

//
//            String username = response.getUsername();
//            ChatRoomViewModel chatRoomViewModel =  new ChatRoomViewModel();
//            ChatRoomState chatRoomState = new ChatRoomState();
//            chatRoomState.setUsername(username);
//            chatRoomViewModel.setChatRoomIdLabel("0");
//            chatRoomViewModel.setUserNameLabel(username);
//            chatRoomViewModel.setState(chatRoomState);
//            chatRoomViewModel.firePropertyChanged();
//            // create a new chat room view
//            createChatRoomFrame(chatRoomViewModel, logOutDataAccessBoundary);
        }



    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
