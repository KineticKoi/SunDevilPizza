//Class verifies if login attempt is made by either an order processing agent, chef employee, or a customer
public class CredentialVerification
{
    // Credentials veriable for "Order Processing Agent"
    private static final String[] dummyOPACredentials = new String[] {"opa", "opa"};
    // Credentials variable for "chef"
    private static final String[] dummyChefCredentials = new String[] {"chef", "chef"};
    
    // Method for checking if login attempt is made by a customer 
    public static Customer customerLoginCheck(String loginType, String userName, String password)
    {
        // Loading customer account from username login input
        Customer customerSave = FileManager.loadCustomer(userName);
        // Checking if customer account exists
        if (customerSave != null)
        {
            // Checking if customer login information is valid for the account
            if (userName.equals(customerSave.getIDNum()) && password.equals(customerSave.getPassword()))
            {
                // Returns customer account information if account exists
                return customerSave;
            }
        }
        // Returns null if customer account does not exist
        return null;
    }
    
    // Method for checking if login attempt is made by an order processing agent or chef employee
    public static Employee employeeLoginCheck(String loginType, String userName, String password)
    {
        // Checking if login type was an employee
        if (loginType.equalsIgnoreCase("employee"))
        {
            // Checking if username and password are associated with a "opa" employee
            if (userName.equals(dummyOPACredentials[0]) && password.equals(dummyOPACredentials[1]))
            {
                // Returns that employee was verified as an Order Processing Agent
                return new Employee("OPA");
            }
            // Checking if username and password are associated with a "chef" employee
            else if (userName.equals(dummyChefCredentials[0]) && password.equals(dummyChefCredentials[1]))
            {
                // Returns that employee was verified as a chef
                return new Employee("CHEF");
            }
        }
        // Returns null if employee login was invalid
        return null;
    }
    
    // Method for chekcing validity of ASURITEID using length of inputted value
    public static boolean isAnAsuriteID(String stringToCheck)
    {
        try
        {
            // Parsing ASURITEID input to check int length
            Integer.parseInt(stringToCheck);
            // Checking if parsed input was 10 digits long
            if (stringToCheck.length() == 10)
            {
                // Returns true if ASURITEID was 10 digits long
                return true;
            }
            else
            {
                // Returns false if ASURITEID was invalid
                return false;
            }
        } 
        // Exception error to catch invalid inputs
        catch (NumberFormatException e)
        {
            // Returns false after catching invalid inputs for failed verification
            return false;
        }
    }
}