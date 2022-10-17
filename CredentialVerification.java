
public class CredentialVerification {
    private static final String[] dummyASURITECredentials = new String[] {"7000", "customer"};
    private static final String[] dummyAdminCredentials = new String[] {"admin", "admin"};
    
    public static String loginCheck(String loginType, String userName, String password) {
        if (loginType.equalsIgnoreCase("employee") && userName.equals(dummyAdminCredentials[0]) && password.equals(dummyAdminCredentials[1])) {
            return "AdminVerified";
        }
        if (loginType.equalsIgnoreCase("asurite") && userName.equals(dummyASURITECredentials[0]) && password.equals(dummyASURITECredentials[1])) {
            return "CustomerVerified";
        }
        return "Denied";
    }
    
    private static boolean isAnInteger(String stringToCheck) { 
        try { 
            Integer.parseInt(stringToCheck); 
            return true; 
        } 
        catch (NumberFormatException e) {  
            return false; 
        }
    }
}
