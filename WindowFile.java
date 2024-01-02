import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowFile extends JFrame {
    public int human, ai; // Number of players (need to be accessed from Main)
    public String[] characters = new String[27]; // Sample characters from CSV file
    //public static boolean isOpen = true;

    public int getHuman() {
        return human;
    } // end of getHuman

    public int getAi() {
        return ai;
    } // end of getAi

    //int cont = 0;
    
    public WindowFile() {
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
                
                System.out.println("hooman: " + human + " robots: " + ai);
                Main.playerList = new Player[human + ai];

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
            setSize(1080, 720);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 1));

            //JLabel chooseCharacterLabel = new JLabel("Choose your character:");
            JLabel chooseCharacterLabel = new JLabel();
            chooseCharacterLabel.setText("Choose your character:");
            chooseCharacterLabel.setFont(new Font("Roboto", Font.BOLD, 12));
            add(chooseCharacterLabel);

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

                            System.out.println("Character = " + character);
                            Main.playerList[0] = CharacterSelection.character(character, "pepito el greninja"); // FALTA EL NOMBRE
                            //cont++;

                            // Close the character selection window
                            dispose();
                        } // end if condition
                    } // end of actionPerformed
                }); // end of addActionListener
                add(characterButton);
            } // end for loop
        } // end of CharacterSelectionWindow
    } // end of CharacterSelectionWindow
} // end of WindowFile