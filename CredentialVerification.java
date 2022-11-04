public class CredentialVerification {
    private static final String[] dummyOPACredentials = new String[] {"opa", "opa"};
    private static final String[] dummyChefCredentials = new String[] {"chef", "chef"};
    
    public static Customer customerLoginCheck(String loginType, String userName, String password) {
        Customer customerSave = FileManager.loadCustomer(userName);
        if (customerSave != null) {
            if (userName.equals(customerSave.getIDNum()) && password.equals(customerSave.getPassword())) {
                return customerSave;
            }
        }
        return null;
    }
    
    public static Employee employeeLoginCheck(String loginType, String userName, String password) {
        if (loginType.equalsIgnoreCase("employee")) {
            if (userName.equals(dummyOPACredentials[0]) && password.equals(dummyOPACredentials[1])) {
                return new Employee("OPA");
            }
            else if (userName.equals(dummyChefCredentials[0]) && password.equals(dummyChefCredentials[1])) {
                return new Employee("CHEF");   
            }
        }
        return null;
    }
    
    public static boolean isAnAsuriteID(String stringToCheck) { 
        try { 
            Integer.parseInt(stringToCheck);
            if (stringToCheck.length() == 9) {
                return true; 
            }
            else {
                return false;
            }
        } 
        catch (NumberFormatException e) {  
            return false; 
        }
    }
}
