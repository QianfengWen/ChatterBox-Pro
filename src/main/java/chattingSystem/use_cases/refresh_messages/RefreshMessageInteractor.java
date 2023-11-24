package chattingSystem.use_cases.refresh_messages;

import chattingSystem.entities.User.User;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.log_out.LogOutInputData;
import chattingSystem.use_cases.log_out.LogOutOutputBoundary;
import chattingSystem.use_cases.log_out.LogOutOutputData;

import java.util.List;

public class RefreshMessageInteractor implements RefreshMessagesInputBoundary{
    private RefreshMessagesDataAccessBoundary refreshMessagesDataAccessBoundary;
    private RefreshMessagesOutputBoundary refreshMessagesOutputBoundary;

    public RefreshMessageInteractor(RefreshMessagesDataAccessBoundary refreshMessagesDataAccessBoundary, RefreshMessagesOutputBoundary refreshMessagesOutputBoundary) {
        this.refreshMessagesDataAccessBoundary = refreshMessagesDataAccessBoundary;
        this.refreshMessagesOutputBoundary = refreshMessagesOutputBoundary;
    }


    @Override
    public void execute() {
        List<String> allMessages = refreshMessagesDataAccessBoundary.fetchAllMessages();
        refreshMessagesOutputBoundary.refreshSuccessView(new RefreshMessageOutputData(allMessages));
    }

}
