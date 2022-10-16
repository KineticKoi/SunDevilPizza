import java.io.Serializable;

//Employee class...
public class Employee extends User implements Serializable {
    private static final long serialVersionUID = 42069L;
    
    //Default Constructor
    Employee() {
        super("employee");
    }
}
