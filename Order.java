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
    
    //Default Constructor
    Order() {}
    
    //Constructor for an already existing pizza
    Order(Pizza pizza) {
        this.pizza = pizza;
    }
    
    //Getter for the pizza object
    public Pizza getPizza() {
        return pizza;
    }
    
    //Setter for pizza pickup time
    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }
    
    //Getter for pizza pickup time
    public String getPickupTime() {
        return pickupTime;
    }
    
    //Getter for pizza status
    public String getStatus() {
        return status;
    }
    
    //Getter for pizza order number
    public String getOrderNumber() {
        return orderNumber;
    }
    
    //Setter for order status
    public void setStatus(String status) {
        this.status = status;
    }
    
    //Getter for email address
    public String getEmail() {
        return emailAddress;
    }

    //Setter for email address
    public void setEmail(String email) {
        this.emailAddress = email;
    }
    
    //Setter for order number
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
