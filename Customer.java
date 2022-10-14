import java.io.Serializable;

//Customer class containing orders and customer info...
public class Customer implements Serializable {
    private static final long serialVersionUID = 42069L;
    //Declaring Variables...
    private int asuriteIDNum;
    private String password;
    private Order[] orders;
}
