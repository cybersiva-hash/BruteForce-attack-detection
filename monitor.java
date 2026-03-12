import java.util.HashMap;
import java.util.Map;

public class monitor {

    private static Map<String, Integer> passwordAttempts = new HashMap<>();
    private static final int SPRAY_THRESHOLD = 3;

    public static void checkPasswordSpray(String password) {

        int attempts = passwordAttempts.getOrDefault(password, 0) + 1;
        passwordAttempts.put(password, attempts);

        if (attempts >= SPRAY_THRESHOLD) {
            System.out.println("⚠ PASSWORD SPRAY ATTACK DETECTED!");
            System.out.println("Multiple accounts attempted with password: " + password);
        }
    }

    public static void main(String[] args) {
        securitymanager security = new securitymanager();
        
        // Test weak password
        String weakPass = "weak";
        System.out.println("Password strength: " + passwordstrengthchecker.getStrength(weakPass));
        
        // Test correct login
        System.out.println("\n--- CORRECT LOGIN ---");
        security.authenticate("admin", "Password123!");
        
        // Test 3 failed attempts (triggers lock)
        System.out.println("\n--- 3 FAILED ATTEMPTS ---");
        security.authenticate("admin", "wrong");
        security.authenticate("admin", "wrong");
        security.authenticate("admin", "wrong"); // LOCKS account
        
        // Test locked account
        System.out.println("\n--- ACCOUNT LOCKED TEST ---");
        security.authenticate("admin", "Password123!");
    }


}
    
