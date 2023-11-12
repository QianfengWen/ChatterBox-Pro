package main.java.chattingSystem.use_cases.create_chat_room;

import main.java.chattingSystem.entities.ChatRoom.ChatRoom;
import main.java.chattingSystem.entities.ChatRoom.ChatRoomFactory;
import main.java.chattingSystem.interface_adapter.presenter.ChatRoomPresenter;

import java.time.LocalDateTime;

public class CreateChatRoomInteractor implements CreateChatRoomInputBoundary{

    final CreateChatRoomOutputBoundary chatRoomPresenter;
    final ChatRoomFactory chatRoomFactory;
    final CreateChatRoomDataAccessBoundary createChatRoomDataAccessBoundary;

    public CreateChatRoomInteractor(CreateChatRoomOutputBoundary chatRoomOutputBoundary,
                                    ChatRoomFactory chatRoomFactory,
                                    CreateChatRoomDataAccessBoundary createChatRoomDataAccessBoundary) {
        this.chatRoomPresenter = chatRoomOutputBoundary;
        this.chatRoomFactory = chatRoomFactory;
        this.createChatRoomDataAccessBoundary = createChatRoomDataAccessBoundary;
    }

    @Override
    public void createChatRoom(CreateChatRoomInputData inputData) {
        if (createChatRoomDataAccessBoundary.existsById(inputData.getChatRoomID())) {
            chatRoomPresenter.prepareFailView("ChatRoom already exists.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            ChatRoom chatRoom = chatRoomFactory.create(inputData.getChatRoomID(), now);
            createChatRoomDataAccessBoundary.save(chatRoom);
            CreateChatRoomOutputData createChatRoomOutputData = new CreateChatRoomOutputData(chatRoom.getChatRoomID());

            chatRoomPresenter.prepareSuccessView(createChatRoomOutputData);
        }
    }
}
