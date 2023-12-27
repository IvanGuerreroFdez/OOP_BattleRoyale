import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    private int human, ai; // Number of players

    public Window() {
        setTitle("Player Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel humanLabel = new JLabel("Enter number of human players: ");
        JTextField humanField = new JTextField();
        add(humanLabel);
        add(humanField);

        JLabel aiLabel = new JLabel("Enter number of AI players: ");
        JTextField aiField = new JTextField();
        add(aiLabel);
        add(aiField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                human = Integer.parseInt(humanField.getText());
                ai = Integer.parseInt(aiField.getText());
                // You can perform further actions with the human and ai values here
            }
        });
        add(submitButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }
}