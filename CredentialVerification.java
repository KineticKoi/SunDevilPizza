
public class CredentialVerification {
    private static final String[] dummyAdminCredentials = new String[] {"admin", "admin"};
    
    public static String loginCheck(String loginType, String userName, String password) {
        if (loginType.equalsIgnoreCase("employee") && userName.equals(dummyAdminCredentials[0]) && password.equals(dummyAdminCredentials[1])) {
            return "AdminVerified";
        }
        return "Denied";
    }
}
