package chattingSystem.use_cases.refresh_messages;

import java.util.List;

public class RefreshMessageOutputData {
    private List<String> message;

    public RefreshMessageOutputData(List<String> message) {
        this.message = message;
    }

    public List<String> getMessags() {return message;}
}
