import java.util.Scanner;

public class LoginSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        User user = new User("admin", "1234");

        System.out.println("===== LOGIN SYSTEM =====");

        while (user.getFailedAttempts() < 3) {

            System.out.print("Enter Username: ");
            String inputUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String inputPassword = scanner.nextLine();

            if (inputUsername.equals(user.getUsername())
                    && user.validatePassword(inputPassword)) {

                System.out.println("Login Success");
                scanner.close();
                return;   // stop program after success

            } else {

                System.out.println("Login Failed");
                System.out.println("Failed Attempts: " + user.getFailedAttempts());
            }
        }

        System.out.println("Account Locked due to 3 failed attempts.");
        scanner.close();
    }
}
