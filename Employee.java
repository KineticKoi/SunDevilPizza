
//Employee class with getter and setter methods for employee role
public class Employee extends User {
    private String role; //role includes order processing agent and chef employees
    
    //Default Constructor
    Employee(String role) {
        super("EMPLOYEE"); //Super to pull employee object information 
        this.role = role; //Updates employee object's role
    }
    
    //Getter method for returning employee role 
    public String getRole() {
        return role;
    }
    
} //End of Employee class
