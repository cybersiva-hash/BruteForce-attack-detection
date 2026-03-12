import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecurityDashboardUI {

    private static int totalAttempts = 0;
    private static int failedAttempts = 0;
    private static int bruteForceDetected = 0;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Cybersecurity Attack Detection Dashboard");
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("Security Monitoring Dashboard");
        title.setBounds(150,20,250,30);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50,80,100,25);

        JTextField userField = new JTextField();
        userField.setBounds(150,80,200,25);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50,120,100,25);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150,120,200,25);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(80,170,120,30);

        JButton bruteButton = new JButton("Run Brute Force");
        bruteButton.setBounds(220,170,150,30);

        JLabel statusLabel = new JLabel("Status: Waiting...");
        statusLabel.setBounds(150,210,250,25);

        JLabel attemptLabel = new JLabel("Total Attempts: 0");
        attemptLabel.setBounds(50,250,200,25);

        JLabel failedLabel = new JLabel("Failed Attempts: 0");
        failedLabel.setBounds(50,280,200,25);

        JLabel attackLabel = new JLabel("Brute Force Detected: 0");
        attackLabel.setBounds(50,310,250,25);

        frame.add(title);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginButton);
        frame.add(bruteButton);
        frame.add(statusLabel);
        frame.add(attemptLabel);
        frame.add(failedLabel);
        frame.add(attackLabel);

        frame.setVisible(true);

        securitymanager security = new securitymanager();

        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String username = userField.getText();
                String password = new String(passField.getPassword());

                totalAttempts++;

                boolean success = security.authenticate(username,password);

                logger.logAttempt(password, success);

                if(success){

                    statusLabel.setText("Status: Login Successful");
                    attemptLabel.setText("Total Attempts: " + totalAttempts);

                } else {

                    failedAttempts++;

                    statusLabel.setText("Status: Login Failed");
                    attemptLabel.setText("Total Attempts: " + totalAttempts);
                    failedLabel.setText("Failed Attempts: " + failedAttempts);

                    if(failedAttempts >= 3){

                        bruteForceDetected++;

                        attackLabel.setText("Brute Force Detected: " + bruteForceDetected);

                        JOptionPane.showMessageDialog(frame,
                                "⚠ Brute Force Attack Detected!",
                                "Security Alert",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        bruteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                User user = new User("admin","Password@123!");

                long startTime = System.currentTimeMillis();

                BruteForceEngine.crackPassword(user,4);

                long endTime = System.currentTimeMillis();

                JOptionPane.showMessageDialog(frame,
                        "Brute Force Simulation Completed\nTime: "
                        +(endTime-startTime)+" ms\nCheck console for summary.",
                        "Attack Summary",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
