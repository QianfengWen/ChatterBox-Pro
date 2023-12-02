import chattingSystem.entities.ChatRoom.ChatRoom;
import chattingSystem.entities.User.User;
import chattingSystem.use_cases.get_chat_room.GetChatRoomDataAccessBoundary;
import chattingSystem.use_cases.get_chat_room.GetUser;
import chattingSystem.use_cases.join_chat_room.*;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesDataAccessBoundary;
import chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class JoinChatRoomInteractorTest {

    @Test
    void testChatRoomDoesNotExist() throws IOException {
        // Mock dependencies
        JoinChatRoomOutputBoundary mockOutputBoundary = mock(JoinChatRoomOutputBoundary.class);
        JoinChatRoomDataAccessBoundary mockDataAccessBoundary = mock(JoinChatRoomDataAccessBoundary.class);
        GetChatRoomDataAccessBoundary mockGetChatRoomDataAccessBoundary = mock(GetChatRoomDataAccessBoundary.class);
        LogOutDataAccessBoundary mockLogOutDataAccessBoundary = mock(LogOutDataAccessBoundary.class);
        SendMessageUserDataAccessInterface mockSendMessageUserDataAccessInterface = mock(SendMessageUserDataAccessInterface.class);
        RefreshMessagesDataAccessBoundary mockRefreshMessagesDataAccessBoundary = mock(RefreshMessagesDataAccessBoundary.class);
        GetUser mockGetUser = mock(GetUser.class);

        // Setup the interactor
        JoinChatRoomInteractor interactor = new JoinChatRoomInteractor(
                mockOutputBoundary,
                mockDataAccessBoundary,
                mockGetChatRoomDataAccessBoundary,
                mockLogOutDataAccessBoundary,
                mockGetUser,
                mockSendMessageUserDataAccessInterface,
                mockRefreshMessagesDataAccessBoundary
        );

        // Define behavior for the scenario where the chat room does not exist
        when(mockDataAccessBoundary.existsById("1")).thenReturn(false);

        // Execute the method to test
        JoinChatRoomInputData inputData = new JoinChatRoomInputData("username");
        interactor.execute(inputData);

        // Verify that no other interactions occur
        verifyNoMoreInteractions(mockOutputBoundary);
    }

    @Test
    void testUserJoinsExistingChatRoom() throws IOException {
        // Mock dependencies
        JoinChatRoomOutputBoundary mockOutputBoundary = mock(JoinChatRoomOutputBoundary.class);
        JoinChatRoomDataAccessBoundary mockDataAccessBoundary = mock(JoinChatRoomDataAccessBoundary.class);
        GetChatRoomDataAccessBoundary mockGetChatRoomDataAccessBoundary = mock(GetChatRoomDataAccessBoundary.class);
        LogOutDataAccessBoundary mockLogOutDataAccessBoundary = mock(LogOutDataAccessBoundary.class);
        SendMessageUserDataAccessInterface mockSendMessageUserDataAccessInterface = mock(SendMessageUserDataAccessInterface.class);
        RefreshMessagesDataAccessBoundary mockRefreshMessagesDataAccessBoundary = mock(RefreshMessagesDataAccessBoundary.class);
        GetUser mockGetUser = mock(GetUser.class);

        // Setup the interactor
        JoinChatRoomInteractor interactor = new JoinChatRoomInteractor(
                mockOutputBoundary,
                mockDataAccessBoundary,
                mockGetChatRoomDataAccessBoundary,
                mockLogOutDataAccessBoundary,
                mockGetUser,
                mockSendMessageUserDataAccessInterface,
                mockRefreshMessagesDataAccessBoundary
        );
        // Similar setup as the previous test

        // Setup mock behavior for existing chat room
        when(mockDataAccessBoundary.existsById("1")).thenReturn(true);
        // Define behavior for the scenario where the chat room does not exist
        ChatRoom mockChatRoom = mock(ChatRoom.class);
        User mockUser = mock(User.class);
        when(mockGetChatRoomDataAccessBoundary.getChatRoom("1")).thenReturn(mockChatRoom);
        when(mockGetUser.getUser("username")).thenReturn(mockUser);
        when(mockChatRoom.getMembers()).thenReturn(new ArrayList<>()); // Empty members list
        List<String> messagesList = Arrays.asList("Message 1", "Message 2");
        when(mockDataAccessBoundary.fetchAllMessages()).thenReturn(messagesList);

        // Execute the method to test
        JoinChatRoomInputData inputData = new JoinChatRoomInputData("username");

        // Mock chat room and user

        interactor.execute(inputData);

        // Verify interactions using ArgumentMatchers
        verify(mockOutputBoundary).prepareSuccessView(
                argThat(outputData -> outputData.getChatRoomId().equals("1") &&
                        outputData.getUser().equals(mockUser) &&
                        outputData.getMessages().equals(messagesList)),
                eq(mockLogOutDataAccessBoundary),
                eq(mockSendMessageUserDataAccessInterface),
                eq(mockRefreshMessagesDataAccessBoundary)
        );
    }

    @Test
    void testUserAlreadyInChatRoom() throws IOException {
        // Similar setup as the previous test
        // Mock dependencies
        JoinChatRoomOutputBoundary mockOutputBoundary = mock(JoinChatRoomOutputBoundary.class);
        JoinChatRoomDataAccessBoundary mockDataAccessBoundary = mock(JoinChatRoomDataAccessBoundary.class);
        GetChatRoomDataAccessBoundary mockGetChatRoomDataAccessBoundary = mock(GetChatRoomDataAccessBoundary.class);
        LogOutDataAccessBoundary mockLogOutDataAccessBoundary = mock(LogOutDataAccessBoundary.class);
        SendMessageUserDataAccessInterface mockSendMessageUserDataAccessInterface = mock(SendMessageUserDataAccessInterface.class);
        RefreshMessagesDataAccessBoundary mockRefreshMessagesDataAccessBoundary = mock(RefreshMessagesDataAccessBoundary.class);
        GetUser mockGetUser = mock(GetUser.class);

        // Setup the interactor
        JoinChatRoomInteractor interactor = new JoinChatRoomInteractor(
                mockOutputBoundary,
                mockDataAccessBoundary,
                mockGetChatRoomDataAccessBoundary,
                mockLogOutDataAccessBoundary,
                mockGetUser,
                mockSendMessageUserDataAccessInterface,
                mockRefreshMessagesDataAccessBoundary
        );
        // Define behavior for the scenario where the chat room does not exist
        // Setup mock behavior for existing chat room
        when(mockDataAccessBoundary.existsById("1")).thenReturn(true);
        // Define behavior for the scenario where the chat room does not exist

        // Mock chat room and user
        ChatRoom mockChatRoom = mock(ChatRoom.class);
        User mockUser = mock(User.class);
        when(mockGetChatRoomDataAccessBoundary.getChatRoom("1")).thenReturn(mockChatRoom);
        when(mockGetUser.getUser("username")).thenReturn(mockUser);
        when(mockChatRoom.getMembers()).thenReturn(new ArrayList<>()); // Empty members list
        List<String> messagesList = Arrays.asList("Message 1", "Message 2");
        when(mockDataAccessBoundary.fetchAllMessages()).thenReturn(messagesList);
        // Execute
        JoinChatRoomInputData inputData = new JoinChatRoomInputData("username");
        interactor.execute(inputData);


        // Verify interactions using ArgumentMatchers
        verify(mockOutputBoundary).prepareSuccessView(
                argThat(outputData -> outputData.getChatRoomId().equals("1") &&
                        outputData.getUser().equals(mockUser) &&
                        outputData.getMessages().equals(messagesList)),
                eq(mockLogOutDataAccessBoundary),
                eq(mockSendMessageUserDataAccessInterface),
                eq(mockRefreshMessagesDataAccessBoundary)
        );
    }

    @Test
    void testGetters() {
        User user = mock(User.class);
        String chatRoomId = "chatRoomId";
        List<String> messages = Arrays.asList("Message 1", "Message 2");
        boolean hasJoined = true;

        JoinChatRoomOutputData outputData = new JoinChatRoomOutputData(user, hasJoined, chatRoomId, messages);

        Assertions.assertEquals(user, outputData.getUser());
        Assertions.assertEquals(chatRoomId, outputData.getChatRoomId());
        Assertions.assertEquals(messages, outputData.getMessages());
        Assertions.assertEquals(hasJoined, outputData.hasJoined);
    }
}
