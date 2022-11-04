
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//Customer class containing orders and customer info...
public class Customer extends User implements Serializable {
    private static final long serialVersionUID = 42069L;
    
    //Declaring Variables...
    private String asuriteIDNum;
    private List<Order> orderHistory;
    private Order currentOrder;
    private String password;
    
    //Default Constructor
    Customer() {}
    
    Customer(String asuriteIDNum) {
        super("CUSTOMER");
        this.asuriteIDNum = asuriteIDNum;
        this.currentOrder = new Order(new Pizza());
        this.orderHistory = new ArrayList<>();
    }
    
    public String getIDNum() {
        return asuriteIDNum;
    }
    
    public void setIDNum(String asuriteIDNum) {
        this.asuriteIDNum = asuriteIDNum;
    }
    
    public void addOrder(Order order) {
        orderHistory.add(order);
    }
    
    public Order getCurrentOrder() {
        return currentOrder;
    }
    
    public void resetCurrentOrder() {
        currentOrder = new Order(new Pizza());
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}
