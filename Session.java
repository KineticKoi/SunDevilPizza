import java.util.Random;
import java.util.UUID;

public class Session
{
    // creates a user
    private User user;
    // creates a random session id
    private final UUID sessionID = UUID.randomUUID();
    // sets the user as null by default
    Session()
    {
        user = null;
    }

    // user getter
    public User getUser()
    {
        return user;
    }
    
    // user setter
    public void setUser(User user)
    {
        this.user = user;
    }
    
    // session ID getter
    public UUID getSessionID()
    {
        return sessionID;
    }

    // generate a unique order number
    public String generateOrderNumber()
    {
        Random rand = new Random();
        // creating a new random number that will serve as the order number
        int num = rand.nextInt(999999);
        // converts the order number to a string filling the blanks with zeros up to 6 digits
        String orderNumber = String.format("%06d", num);
        // creates a delimiter between the zeros and the order number
        orderNumber = orderNumber.substring(0, 2) + "-" + orderNumber.substring(2);
        // returns the order number
        return orderNumber;
    }
}