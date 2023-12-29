package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    private int human, ai; // Number of players

    public Window() {
        setTitle("Player Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
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

                // Create a new window for difficulty level selection
                DifficultyWindow difficultyWindow = new DifficultyWindow();
                difficultyWindow.setVisible(true);
            }
        });
        add(submitButton);
    }

    // Difficulty level
    private class DifficultyWindow extends JFrame {
        public DifficultyWindow() {
            setTitle("Select Difficulty");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(250, 150);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 1));

            JLabel messageLabel = new JLabel("How brave are you?");
            add(messageLabel);

            JButton easyButton = new JButton("Easy");
            easyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Show character selection window
                    CharacterSelectionWindow characterWindow = new CharacterSelectionWindow();
                    characterWindow.setVisible(true);

                    // Close the difficulty window
                    dispose();
                }
            });
            add(easyButton);

            JButton normalButton = new JButton("Normal");
            normalButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Show character selection window
                    CharacterSelectionWindow characterWindow = new CharacterSelectionWindow();
                    characterWindow.setVisible(true);

                    // Close the difficulty window
                    dispose();
                }
            });
            add(normalButton);

            JButton hardButton = new JButton("Hard");
            hardButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Show character selection window
                    CharacterSelectionWindow characterWindow = new CharacterSelectionWindow();
                    characterWindow.setVisible(true);

                    // Close the difficulty window
                    dispose();
                }
            });
            add(hardButton);
        }
    }

    // Inner class for character selection window
    private class CharacterSelectionWindow extends JFrame {
        public CharacterSelectionWindow() {
            setTitle("Choose Your Character");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 200);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 1));

            JLabel chooseCharacterLabel = new JLabel("Choose your character:");
            add(chooseCharacterLabel);

            // Sample characters from CSV file
            String[] characters = {"Soldier", "Archer", "Wizard"};

            for (String character : characters) {
                JButton characterButton = new JButton(character);
                characterButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Show confirmation window
                        int option = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to choose " + character + "?",
                                "Confirmation", JOptionPane.YES_NO_OPTION);

                        if (option == JOptionPane.YES_OPTION) {
                            // Show confirmation message
                            JOptionPane.showMessageDialog(null, "You have chosen " + character);

                            // Close the character selection window
                            dispose();
                        }
                    }
                });
                add(characterButton);
            }
        }
    }
}