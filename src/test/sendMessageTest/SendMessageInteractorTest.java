package sendMessageTest;

import chattingSystem.entities.Message.Message;
import chattingSystem.entities.Message.MessageFactory;
import chattingSystem.use_cases.send_message.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDateTime;

class SendMessageInteractorTest {

    @Mock
    private SendMessageUserDataAccessInterface mockUserDataAccessInterface;
    @Mock
    private SendMessageOutputBoundary mockSendMessagePresenter;
    @Mock
    private MessageFactory mockMessageFactory;
    @Mock
    private Message mockMessage;

    private SendMessageInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new SendMessageInteractor(mockUserDataAccessInterface, mockSendMessagePresenter, mockMessageFactory);
    }

    @Test
    void testExecute() throws IOException {
        String chatRoomId = "chatRoomId";
        SendMessageInputData inputData = new SendMessageInputData("username", "senderId", "message", LocalDateTime.now());
        LocalDateTime now = LocalDateTime.now();
        String messageId = "messageId";
        ArgumentCaptor<SendMessageOutputData> captor = ArgumentCaptor.forClass(SendMessageOutputData.class);


        // Ensure that the mock factory returns the mock message
        when(mockUserDataAccessInterface.generateMessageid(chatRoomId)).thenReturn(messageId);
        when(mockMessageFactory.create(
                eq(messageId),
                eq(inputData.getSenderID()),
                eq(inputData.getUsername()),
                any(inputData.getTimestap().getClass()),
                eq(inputData.getMessage())
        )).thenReturn(mockMessage);

        interactor.execute(inputData, chatRoomId);



        // Verify that the message is created and saved
        verify(mockMessageFactory).create(
                eq(messageId),
                eq(inputData.getSenderID()),
                eq(inputData.getUsername()),
                any(inputData.getTimestap().getClass()),
                eq(inputData.getMessage())
        );
        verify(mockUserDataAccessInterface).save(eq(messageId), eq(mockMessage));

        verify(mockSendMessagePresenter).prepareSuccessView(captor.capture());
        // Retrieve the captured argument and assert its properties
        SendMessageOutputData capturedOutputData = captor.getValue();
        Assertions.assertEquals("senderId", capturedOutputData.getSenderId());
        Assertions.assertEquals("username", capturedOutputData.getUsername());
        Assertions.assertEquals(chatRoomId, capturedOutputData.getChatRoomId());
        Assertions.assertEquals("message", capturedOutputData.getMessage());
        Assertions.assertFalse(capturedOutputData.isUseCaseFailed());
    }
}
