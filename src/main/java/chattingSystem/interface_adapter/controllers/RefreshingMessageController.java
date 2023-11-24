package chattingSystem.interface_adapter.controllers;

import chattingSystem.use_cases.refresh_messages.RefreshMessageInputData;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesInputBoundary;

import java.io.IOException;

public class RefreshingMessageController {
    private final RefreshMessagesInputBoundary refreshMessagesInteractor;

    public RefreshingMessageController(RefreshMessagesInputBoundary refreshMessagesInteractor) {
        this.refreshMessagesInteractor = refreshMessagesInteractor;
    }

    public void execute(){
        RefreshMessageInputData refreshMessageInputData = new RefreshMessageInputData();
        refreshMessagesInteractor.execute();
    }
}
