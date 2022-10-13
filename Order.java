
import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 42069L;
    private Pizza pizza;
    private int orderNumber;
    private int orderTotal;
}
