
import java.util.Random;
import java.util.UUID;

public class Session {
    private User user;
    private final UUID sessionID = UUID.randomUUID();
    Session() {
        user = null;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public UUID getSessionID() {
        return sessionID;
    }
    
    public String generateOrderNumber() {
        Random rand = new Random();
        int num = rand.nextInt(999999);
        String orderNumber = String.format("%06d", num);
        orderNumber = orderNumber.substring(0, 2) + "-" + orderNumber.substring(2);
        return orderNumber;
    }
}

