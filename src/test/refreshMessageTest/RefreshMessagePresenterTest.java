package refreshMessageTest;

import chattingSystem.interface_adapter.presenter.RefreshMessagePresenter;
import chattingSystem.interface_adapter.state.ChatRoomState;
import chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import chattingSystem.use_cases.refresh_messages.RefreshMessageOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class RefreshMessagePresenterTest {

    @Mock
    private ChatRoomViewModel mockChatRoomViewModel;
    @Mock
    private ChatRoomState mockChatRoomState;

    private RefreshMessagePresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new RefreshMessagePresenter(mockChatRoomViewModel, mockChatRoomState);
    }

    @Test
    void testRefreshSuccessView() {
        List<String> newMessages = Arrays.asList("Message 1", "Message 2");
        RefreshMessageOutputData mockOutputData = new RefreshMessageOutputData(newMessages);

        presenter.refreshSuccessView(mockOutputData);

        // Verify that the ChatRoomState is updated with new messages
        verify(mockChatRoomState).setMessage(newMessages);
        // Verify that the ChatRoomViewModel is updated with the new state
        verify(mockChatRoomViewModel).setState(mockChatRoomState);
    }
}
