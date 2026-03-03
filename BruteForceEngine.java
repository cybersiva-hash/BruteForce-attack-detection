public class BruteForceEngine {

    private static long totalAttempts = 0;
    private static boolean cracked = false;

    public static void crackPassword(User user, int maxLength) {

        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";

        long startTime = System.currentTimeMillis();

        for (int length = 1; length <= maxLength; length++) {

            char[] attempt = new char[length];
            generate(user, characters, attempt, 0);

            if (cracked) break;
        }

        long endTime = System.currentTimeMillis();

        System.out.println("\n===== Attack Summary =====");
        System.out.println("Total Attempts: " + totalAttempts);
        System.out.println("Time Taken: " + (endTime - startTime) + " ms");
    }

    private static void generate(User user, String characters, char[] attempt, int position) {

        if (cracked) return;

        if (position == attempt.length) {

            totalAttempts++;

            String attemptStr = new String(attempt);

            if (user.validatePassword(attemptStr)) {
                System.out.println("\nPassword Cracked!");
                System.out.println("Password is: " + attemptStr);
                cracked = true;
            }

            return;
        }

        for (int i = 0; i < characters.length(); i++) {

            attempt[position] = characters.charAt(i);
            generate(user, characters, attempt, position + 1);
        }
    }
}