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
    
    //Settter for the pizza type
    public void setType(String type) {
        this.type = type;
    }
    
    //Setter for the pizza size
    public void setSize(String size) {
        this.size = size;
    }
    
    //Getter for pizza type
    public String getType() {
        return type;
    }
    
    //Getter for pizza size
    public String getSize() {
        return size;
    }
    
    //Getter for pizza toppings
    public List getToppings() {
        return toppings;
    }
    
    //Method to add a topping to toppings ArrayList
    public void addTopping(String topping) {
        this.toppings.add(topping);
    }
    
    //Method for removing a topping from toppings ArrayList
    public void removeTopping(String topping) {
        for (int i = 0; i < toppings.size(); i++) {
            if(toppings.get(i).contains(topping)) {
                toppings.remove(i);
            }
        }
    }
}
