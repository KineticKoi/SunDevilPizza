import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// Pizza class containing specific pie information
public class Pizza implements Serializable
{
    private static final long serialVersionUID = 42069L;
    
    // Declaring Variables
    private String type;
    private String size;
    private List<String> toppings = new ArrayList();
    
    // Default Constructor
    Pizza() {}
    
    // setter for pizza type
    public void setType(String type)
    {
        this.type = type;
    }

    // setter for pizza size
    public void setSize(String size)
    {
        this.size = size;
    }
    
    // getter for pizza type
    public String getType()
    {
        return type;
    }
    
    // getter for pizza size
    public String getSize()
    {
        return size;
    }
    
    // getter for pizza toppings
    public List getToppings()
    {
        return toppings;
    }
    
    // adding a topping to toppings
    public void addTopping(String topping)
    {
        this.toppings.add(topping);
    }
    
    public void removeTopping(String topping)
    {
        // iterating through the toppings list seeing if the topping exists
        for (int i = 0; i < toppings.size(); i++)
        {
            // if the topping exists
            if(toppings.get(i).contains(topping))
            {
                // remove said topping
                toppings.remove(i);
            }
        }
    }
}