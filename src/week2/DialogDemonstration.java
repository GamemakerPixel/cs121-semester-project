package week2;

import javax.swing.*;

public class DialogDemonstration {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter a name: ");
        JOptionPane.showMessageDialog(null,"Your name is " + name);

    }
}
