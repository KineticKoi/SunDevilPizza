
import java.io.Serializable;


//Order containing a pizza, order # and total amount
public class Order implements Serializable {
    private static final long serialVersionUID = 42069L;
    
    //Declaring Variables...
    private Pizza pizza;
    private String status;
    private String orderNumber;
    private String pickupTime;
    private double orderTotal;
    private String emailAddress;
    
    Order() {}
    
    Order(Pizza pizza) {
        this.pizza = pizza;
    }
    
    public Pizza getPizza() {
        return pizza;
    }
    
    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }
    
    public String getPickupTime() {
        return pickupTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getOrderNumber() {
        return orderNumber;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getEmail() {
        return emailAddress;
    }
    
    public void setEmail(String email) {
        this.emailAddress = email;
    }
    
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
