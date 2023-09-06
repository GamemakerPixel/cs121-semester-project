package week3.logicalOperators;


import javax.swing.*;

public class Login {
    // Don't store credentials like this!
    private static final String validUsername = "Turtles";
    private static final String validPassword = "Tu7t13s";

    public static void main(String[] args) {
        String username;
        String password;

        username = JOptionPane.showInputDialog("Enter your username: ");
        password = JOptionPane.showInputDialog("Enter your password: ");

        if (username.equals(validUsername) && password.equals(validPassword)){
            JOptionPane.showMessageDialog(null, "Welcome to CS121.");
        }
        else if (!username.equals(validUsername) && !password.equals(validPassword)){
            JOptionPane.showMessageDialog(null, "Invalid username and password.");
        }
        else if (!username.equals(validUsername)){
            JOptionPane.showMessageDialog(null, "Invalid username.");
        }
        //Could be done with an else, done with else if for readability.
        else if (!password.equals(validPassword)){
            JOptionPane.showMessageDialog(null, "Invalid password.");
        }
    }
}
