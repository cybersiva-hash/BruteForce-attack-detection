import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    private String username;
    private String passwordHash;
    private int failedAttempts;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.passwordHash = hashPassword(password);
        this.failedAttempts = 0;
    }

    // Hash password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Validate password
    public boolean validatePassword(String inputPassword) {

        String inputHash = hashPassword(inputPassword);

        if (inputHash.equals(passwordHash)) {
            failedAttempts = 0;  // reset counter on success
            return true;
        } else {
            failedAttempts++;
            return false;
        }
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public String getUsername() {
        return username;
    }
}
