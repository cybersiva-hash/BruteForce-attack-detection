package security;

import java.util.HashMap;
import java.util.Map;

public class SecurityManager {
    private Map<String, Integer> failedAttempts = new HashMap<>();
    private Map<String, Long> lockTimes = new HashMap<>();
    private static final int MAX_ATTEMPTS = 3;
    private static final long LOCK_DURATION = 5 * 60 * 1000L; // 5 minutes
    private static final long DELAY_MS = 2000L; // 2 seconds

    public boolean authenticate(String username, String password) {
        if (isAccountLocked(username)) {
            System.out.println("Account is LOCKED for 5 minutes");
            return false;
        }

        boolean isValid = validatePassword(username, password);
        
        if (isValid) {
            resetAttempts(username);
            System.out.println("✅ Login SUCCESSFUL");
            return true;
        } else {
            incrementAttempts(username);
            try {
                Thread.sleep(DELAY_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("❌ Login FAILED - Attempts: " + failedAttempts.get(username));
            return false;
        }
    }

    private boolean validatePassword(String username, String password) {
        return "admin".equals(username) && "Password123!".equals(password);
    }

    private void incrementAttempts(String username) {
        int attempts = failedAttempts.getOrDefault(username, 0) + 1;
        failedAttempts.put(username, attempts);
        
        if (attempts >= MAX_ATTEMPTS) {
            lockAccount(username);
        }
    }

    private void lockAccount(String username) {
        lockTimes.put(username, System.currentTimeMillis());
        System.out.println("🔒 Account LOCKED: " + username);
    }

    private boolean isAccountLocked(String username) {
        Long lockTime = lockTimes.get(username);
        if (lockTime != null && (System.currentTimeMillis() - lockTime) < LOCK_DURATION) {
            return true;
        }
        lockTimes.remove(username);
        failedAttempts.remove(username);
        return false;
    }

    private void resetAttempts(String username) {
        failedAttempts.remove(username);
        lockTimes.remove(username);
    }
} 
