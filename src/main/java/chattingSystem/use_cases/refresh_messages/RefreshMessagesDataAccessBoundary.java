package chattingSystem.use_cases.refresh_messages;

import java.util.List;

public interface RefreshMessagesDataAccessBoundary {
    List<String> fetchAllMessages();
}
