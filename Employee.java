
//Employee class with getter and setter methods for employee role
public class Employee extends User {
    private String role; //role can be either chef or order processing agent
    
    //Default Constructor
    Employee(String role) {
        super("EMPLOYEE"); //Super for class User 
        this.role = role; //Updates employee object's role
    }
    
    //Getter method for returning employee role 
    public String getRole() {
        return role;
    }
    
} //End of Employee class
