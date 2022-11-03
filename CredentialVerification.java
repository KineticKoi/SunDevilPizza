
public class CredentialVerification {
    private static final String[] dummyAdminCredentials = new String[] {"employee", "employee"};
    
    public static Customer loginCheck(String loginType, String userName, String password) {
        if (loginType.equalsIgnoreCase("employee") && userName.equals(dummyAdminCredentials[0]) && password.equals(dummyAdminCredentials[1])) {
            return null;
        }
        if (loginType.equalsIgnoreCase("asurite")) {
            Customer customerSave = FileManager.loadCustomer(Integer.valueOf(userName));
            if (customerSave != null) {
                if (Integer.parseInt(userName) == customerSave.getIDNum() && password.equals(customerSave.getPassword())) {
                    return customerSave;
                }
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
