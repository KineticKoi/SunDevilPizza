import java.io.Serializable;

//Customer class containing orders and customer info...
public class Customer extends User implements Serializable {
    private static final long serialVersionUID = 42069L;
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
