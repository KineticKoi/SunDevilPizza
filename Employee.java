
//Employee class...
public class Employee extends User {
    private String role;
    
    //Default Constructor
    Employee(String role) {
        super("EMPLOYEE");
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
}
