import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String LOG_FILE = "log.txt";

    // Method to log attack attempts
    public static void logAttempt(String attemptedPassword, boolean success) {

        try {
            FileWriter fw = new FileWriter(LOG_FILE, true); // append mode
            PrintWriter pw = new PrintWriter(fw);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String status = success ? "SUCCESS" : "FAIL";

            pw.println("Date & Time : " + now.format(formatter));
            pw.println("Attempted Password : " + attemptedPassword);
            pw.println("Status : " + status);
            pw.println("----------------------------------------");

            pw.close();

        } catch (IOException e) {
            System.out.println("Error writing to log file.");
        }
    }
}