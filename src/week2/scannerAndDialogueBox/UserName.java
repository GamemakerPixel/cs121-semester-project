package week2.scannerAndDialogueBox;

import javax.swing.*;

public class UserName {
    public static void main(String[] args) {
        String firstName = JOptionPane.showInputDialog("Enter your first name: ");
        String lastName = JOptionPane.showInputDialog("Enter your last name: ");

        String name = firstName + " " + lastName;

        StringBuilder nameStringBuilder = new StringBuilder(name);

        JOptionPane.showMessageDialog(null, "Your name capitalized: " + name.toUpperCase() + "\n" +
                "Your name in lower case: " + name.toLowerCase() + "\n" +
                "Your name reversed: " + nameStringBuilder.reverse() + "\n");
    }
}
