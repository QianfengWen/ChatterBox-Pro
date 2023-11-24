package chattingSystem.use_cases.refresh_messages;

import java.io.IOException;
import java.util.List;

public interface RefreshMessagesDataAccessBoundary {
    List<String> fetchAllMessages();


}
