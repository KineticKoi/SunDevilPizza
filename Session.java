import java.util.Random;
import java.util.UUID;

//Session class to maintain a single user experience
public class Session {

    //Declaring Variables...
    private User user;
    private final UUID sessionID = UUID.randomUUID();
    
    //Default Constructor
    Session() {
        user = null;
    }
    
    //Getter for the user object
    public User getUser() {
        return user;
    }
    
    //Setter for the user object
    public void setUser(User user) {
        this.user = user;
    }
    
    //Getter for the session ID variable
    public UUID getSessionID() {
        return sessionID;
    }

    //Random number generator for an order number
    public String generateOrderNumber() {
        Random rand = new Random();
        int num = rand.nextInt(999999);
        String orderNumber = String.format("%06d", num);
        orderNumber = orderNumber.substring(0, 2) + "-" + orderNumber.substring(2);
        return orderNumber;
    }
}

