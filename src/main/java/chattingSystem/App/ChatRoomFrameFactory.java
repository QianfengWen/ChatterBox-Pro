package chattingSystem.App;


import chattingSystem.entities.Message.TextMessageFactory;
import chattingSystem.frameworks_drivers.ui.views.ChatRoomView;
import chattingSystem.frameworks_drivers.ui.views.ChatRoomViewManager;
import chattingSystem.interface_adapter.controllers.*;
import chattingSystem.interface_adapter.presenter.*;
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
import chattingSystem.use_cases.show_joke.ShowJokeInputBoundry;
import chattingSystem.use_cases.show_joke.ShowJokeInteractor;
import chattingSystem.use_cases.show_joke.ShowJokeOutputBoundry;
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
        chatRoomFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout chatRoomCardLayout = new CardLayout();
        JPanel chatRoomViews = new JPanel(chatRoomCardLayout);
        chatRoomFrame.add(chatRoomViews);
        // This keeps track of and manages which view is currently showing.
        ChatRoomViewManagerModel chatRoomViewManagerModel = new ChatRoomViewManagerModel();
        ChatRoomViewManager chatRoomViewManager = new ChatRoomViewManager(chatRoomViews, chatRoomCardLayout, chatRoomViewManagerModel);
        LogOutController logOutController = createLogOutUseCase(logOutDataAccessBoundary);
        GetWeatherViewModel getWeatherViewModel = new GetWeatherViewModel();
        ShowWeatherController showWeatherController = createShowWeatherUseCase(getWeatherViewModel);
        FunJokeViewModel funJokeViewModel = new FunJokeViewModel();
        ShowJokeController showJokeController = createShowJokeUseCase(funJokeViewModel);
        SendMessageController sendMessageController = createSendMessageController(sendMessageUserDataAccessInterface, chatRoomViewModel);
        RefreshingMessageController refreshingMessageController = createRefreshingMessageController(RefreshMessagesDataAccessBoundary, chatRoomViewModel, chatRoomViewModel.getState());
        ChatRoomView chatRoomView = new ChatRoomView(chatRoomViewModel, chatRoomViewManagerModel, chatRoomViewManager, logOutController, showWeatherController, sendMessageController, refreshingMessageController, showJokeController);
        chatRoomViews.add(chatRoomView, chatRoomView.viewName);
        chatRoomFrame.pack();
        chatRoomFrame.setVisible(true);
        chatRoomViewModel.setCurrentframe(chatRoomFrame);
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
    public static ShowJokeController createShowJokeUseCase(FunJokeViewModel funJokeViewModel
    ) {

        // Notice how we pass this method's parameters to the Presenter.
        ShowJokeOutputBoundry showJokeOutputBoundry = new FunJokePresenter(funJokeViewModel);

        ShowJokeInputBoundry showJokeInteractor = new ShowJokeInteractor(showJokeOutputBoundry);

        return new ShowJokeController(showJokeInteractor);
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


