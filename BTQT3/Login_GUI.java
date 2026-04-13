import javax.swing.*;

public class Login_GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 170);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        // Username section
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 20, 80, 25);
        frame.add(userLabel);

        JTextField userField = new JTextField(20);
        userField.setBounds(130, 20, 120, 25);
        frame.add(userField);


        // Password section
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 50, 80, 25);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setEchoChar('\u2022');
        passwordField.setBounds(130, 50, 120, 25);
        frame.add(passwordField);


        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(170, 90, 80, 25);
        frame.add(loginButton);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(20, 90, 100, 25);
        frame.add(registerButton);

        frame.setVisible(true);
    }
}
