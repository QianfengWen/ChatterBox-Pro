package chattingSystem.entities.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface User {
    String getUsername();
    String getUserid();
    String getPassword();
    LocalDateTime getCreationTime();
    boolean getIsOnline();
    void setOnline(boolean isOnline);

}
