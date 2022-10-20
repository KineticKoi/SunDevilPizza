
//Customer class containing orders and customer info...
public class Customer extends User {
    //Declaring Variables...
    private int asuriteIDNum;
    private Order[] orders;
    
    //Default Constructor
    Customer(int asuriteIDNum) {
        super("CUSTOMER");
        this.asuriteIDNum = asuriteIDNum;
    }
    
    public int getIDNum() {
        return asuriteIDNum;
    }
}
