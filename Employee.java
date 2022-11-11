// Class with getter and setter methods for employee role
public class Employee extends User
{
    // Role can be either Chef or Order Processing Agent
    private String role;
    
    // Default Constructor
    Employee(String role)
    {
        super("EMPLOYEE");
        // Updating employee role from string in paratemeter list
        this.role = role;
    }
    
    // Getter method for returning employee role
    public String getRole()
    {
        return role;
    }
    
}