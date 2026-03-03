package security;

public class Monitor {
    public static void main(String[] args) {
        SecurityManager security = new SecurityManager();
        
        // Test weak password
        String weakPass = "weak";
        System.out.println("Password strength: " + PasswordStrengthChecker.getStrength(weakPass));
        
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
