
//Class verifies if login attempt is made by either a chef employee, order processing agent, or a customer
public class CredentialVerification {
    private static final String[] dummyOPACredentials = new String[] {"opa", "opa"}; //Credentials veriable for "Order Processing Agent"
    private static final String[] dummyChefCredentials = new String[] {"chef", "chef"}; //Credentials variable for "chef"
    
    //Method for checking if login attempt is made by a customer
    public static Customer customerLoginCheck(String loginType, String userName, String password) {
        Customer customerSave = FileManager.loadCustomer(userName); //Loading customer account from login inputs
        if (customerSave != null) { //Checking information is not null, if it is then method will return null for verifcation
            if (userName.equals(customerSave.getIDNum()) && password.equals(customerSave.getPassword())) { //Checking if cutsomer login info exists in the system
                return customerSave; //Returns verification that customer's account exists in the system
            }
        }
        return null; //Returns null if customer account does not exist
    }
    
    //Method for checking if login attempt is made by an order processing agent or chef employee 
    public static Employee employeeLoginCheck(String loginType, String userName, String password) { 
        if (loginType.equalsIgnoreCase("employee")) { //Checking if login type was an employee
            if (userName.equals(dummyOPACredentials[0]) && password.equals(dummyOPACredentials[1])) { //Checking if user name is associated with a order processing agent
                return new Employee("OPA"); //Returning verification value for an order processing agent 
            }
            else if (userName.equals(dummyChefCredentials[0]) && password.equals(dummyChefCredentials[1])) { //Checking if user name is associated with a chef employee 
                return new Employee("CHEF"); //Returning verification value for an chef employee
            }
        }
        return null; //Returning null if login type was not an employee
    }
    
    //Method for checking validity of ASURITEID inputs from customer user login 
    public static boolean isAnAsuriteID(String stringToCheck) { 
        try { 
            Integer.parseInt(stringToCheck); //Parsing ASURITEID input to check length
            if (stringToCheck.length() == 9) { //Checking if parsed ASURITEID is 9 integers long
                return true; //Returning true if ID is valid
            }
            else {
                return false; //Returning false if ID is invalid
            }
        } 
        catch (NumberFormatException e) {  
            return false; //Returning false if ID input was empty or included letters
        }
    }
    
}//End of CredentialVerification class

