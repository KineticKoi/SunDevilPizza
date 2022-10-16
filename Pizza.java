
import java.io.Serializable;

public class Pizza implements Serializable {
    private String type;
    private String size;
    private String[] toppings;
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }
}
