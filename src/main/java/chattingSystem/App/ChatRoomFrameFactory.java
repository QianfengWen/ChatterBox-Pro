package chattingSystem.App;


import chattingSystem.entities.Message.TextMessageFactory;
import chattingSystem.frameworks_drivers.ui.views.ChatRoomView;
import chattingSystem.frameworks_drivers.ui.views.ChatRoomViewManager;
import chattingSystem.interface_adapter.controllers.LogOutController;
import chattingSystem.interface_adapter.controllers.RefreshingMessageController;
import chattingSystem.interface_adapter.controllers.SendMessageController;
import chattingSystem.interface_adapter.controllers.ShowWeatherController;
import chattingSystem.interface_adapter.presenter.ChatRoomPresenter;
import chattingSystem.interface_adapter.presenter.GetWeatherPresenter;
import chattingSystem.interface_adapter.presenter.RefreshMessagePresenter;
import chattingSystem.interface_adapter.presenter.SendMessagePresenter;
import chattingSystem.interface_adapter.state.ChatRoomState;
import chattingSystem.interface_adapter.view_models.*;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.log_out.LogOutInputBoundary;
import chattingSystem.use_cases.log_out.LogOutInteractor;
import chattingSystem.use_cases.log_out.LogOutOutputBoundary;
import chattingSystem.use_cases.refresh_messages.RefreshMessageInteractor;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesDataAccessBoundary;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesInputBoundary;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesOutputBoundary;
import chattingSystem.use_cases.send_message.SendMessageInputBoundary;
import chattingSystem.use_cases.send_message.SendMessageInteractor;
import chattingSystem.use_cases.send_message.SendMessageOutputBoundary;
import chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;
import chattingSystem.use_cases.show_weather.ShowWeatherInputBoundary;
import chattingSystem.use_cases.show_weather.ShowWeatherInteractor;
import chattingSystem.use_cases.show_weather.ShowWeatherOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ChatRoomFrameFactory {
    private static JFrame currentChatRoomFrame;
    public static void createChatRoomFrame(ChatRoomViewModel chatRoomViewModel,
                                           LogOutDataAccessBoundary logOutDataAccessBoundary,
                                           SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface,
                                           RefreshMessagesDataAccessBoundary RefreshMessagesDataAccessBoundary) throws IOException {
        JFrame chatRoomFrame = new JFrame("Chat Room");
        chatRoomFrame.setVisible(false);
        CardLayout chatRoomCardLayout = new CardLayout();
        JPanel chatRoomViews = new JPanel(chatRoomCardLayout);
        chatRoomFrame.add(chatRoomViews);
        // This keeps track of and manages which view is currently showing.
        ChatRoomViewManagerModel chatRoomViewManagerModel = new ChatRoomViewManagerModel();
        ChatRoomViewManager chatRoomViewManager = new ChatRoomViewManager(chatRoomViews, chatRoomCardLayout, chatRoomViewManagerModel);
        LogOutController logOutController = createLogOutUseCase(logOutDataAccessBoundary);
        GetWeatherViewModel getWeatherViewModel = new GetWeatherViewModel();
        ShowWeatherController showWeatherController = createShowWeatherUseCase(getWeatherViewModel);
        SendMessageController sendMessageController = createSendMessageController(sendMessageUserDataAccessInterface, chatRoomViewModel);
        RefreshingMessageController refreshingMessageController = createRefreshingMessageController(RefreshMessagesDataAccessBoundary, chatRoomViewModel, chatRoomViewModel.getState());
        chatRoomViewModel.setCurrentframe(chatRoomFrame);
        ChatRoomView chatRoomView = new ChatRoomView(chatRoomViewModel, chatRoomViewManagerModel, chatRoomViewManager, logOutController, showWeatherController, sendMessageController, refreshingMessageController);
        chatRoomViews.add(chatRoomView, chatRoomView.viewName);
        chatRoomFrame.pack();
        chatRoomFrame.setVisible(true);
        currentChatRoomFrame = chatRoomFrame;
    }

    public static LogOutController createLogOutUseCase(
            LogOutDataAccessBoundary logOutDataAccessBoundary
            ) {

        // Notice how we pass this method's parameters to the Presenter.
        LogOutOutputBoundary logOutOutputBoundary = new ChatRoomPresenter();

        LogOutInputBoundary logOutInteractor = new LogOutInteractor(logOutOutputBoundary, logOutDataAccessBoundary);

        return new LogOutController(logOutInteractor);
    }
    public static ShowWeatherController createShowWeatherUseCase(GetWeatherViewModel getWeatherViewModel
    ) {

        // Notice how we pass this method's parameters to the Presenter.
        ShowWeatherOutputBoundary showWeatherOutputBoundary = new GetWeatherPresenter(getWeatherViewModel);

        ShowWeatherInputBoundary showWeatherInteractor = new ShowWeatherInteractor(showWeatherOutputBoundary);

        return new ShowWeatherController(showWeatherInteractor);
    }
    public static SendMessageController createSendMessageController(
            SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface, ChatRoomViewModel chatRoomViewModel
    ) {

        // Notice how we pass this method's parameters to the Presenter.
        SendMessageOutputBoundary sendMessageOutputBoundary = new SendMessagePresenter(chatRoomViewModel);

        TextMessageFactory textMessageFactory = new TextMessageFactory();

        SendMessageInputBoundary sendMessageInputBoundary = new SendMessageInteractor(sendMessageUserDataAccessInterface, sendMessageOutputBoundary, textMessageFactory);

        return new SendMessageController(sendMessageInputBoundary);
    }

    public static RefreshingMessageController createRefreshingMessageController(
            RefreshMessagesDataAccessBoundary refreshMessagesDataAccessBoundary, ChatRoomViewModel chatRoomViewModel, ChatRoomState chatRoomState
    ) {

        // Notice how we pass this method's parameters to the Presenter.
        RefreshMessagesOutputBoundary refreshMessagesOutputBoundary = new RefreshMessagePresenter(chatRoomViewModel, chatRoomState);

        RefreshMessagesInputBoundary refreshMessageInputBoundary = new RefreshMessageInteractor(refreshMessagesDataAccessBoundary,
                refreshMessagesOutputBoundary);

        return new RefreshingMessageController(refreshMessageInputBoundary);
    }
}


