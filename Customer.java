import java.io.Serializable;


public class Customer implements Serializable {
    private static final long serialVersionUID = 42069L;
    private int asuriteIDNum;
    private String password;
    private Order[] orders;
}
