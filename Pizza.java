
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//Pizza class containing specific pie information
public class Pizza implements Serializable {
    private static final long serialVersionUID = 42069L;
    
    //Declaring Variables...
    private String type;
    private String size;
    private List<String> toppings = new ArrayList();
    
    //Default Constructor
    Pizza() {}
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public String getType() {
        return type;
    }
    
    public String getSize() {
        return size;
    }
    
    public List getToppings() {
        return toppings;
    }
    
    public void addTopping(String topping) {
        this.toppings.add(topping);
    }
    
    public void removeTopping(String topping) {
        for (int i = 0; i < toppings.size(); i++) {
            if(toppings.get(i).contains(topping)) {
                toppings.remove(i);
            }
        }
    }
}
