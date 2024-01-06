import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class WindowFile extends JFrame {
    public int human, ai; // Number of players (need to be accessed from Main)
    public String[] characters = new String[27]; // Sample characters from CSV file
    public boolean allCharactersSelected = false; // If all human characters have been selected
    public boolean hardMode = false;

    private final Object lock = new Object();

    public Object getLock() {
        return lock;
    }

    public boolean allCharactersSelected() {
        return allCharactersSelected;
    }

    public int getHuman() {
        return human;
    } // end of getHuman

    public int getAi() {
        return ai;
    } // end of getAi
    
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
                
                System.out.println("human: " + human + " robots: " + ai);
                Main.playerList = new ArrayList<Player>();

                // Create a new window for difficulty level selection
                DifficultyWindow difficultyWindow = new DifficultyWindow();
                difficultyWindow.setVisible(true);
            } // end of actionPerformed
        }); // end of addActionListener
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

                    // Close the difficulty window
                    dispose();
                    JOptionPane.showMessageDialog(null, "You automatically win, was too easy!");
                    System.out.println("Easy mode = insta win!");
                    System.exit(0);
                } // end of actionPerformed
            }); // end of addActionListener
            add(easyButton);

            JButton normalButton = new JButton("Normal");
            normalButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Show character selection window
                    CharacterSelectionWindow characterWindow = new CharacterSelectionWindow(1);
                    characterWindow.setVisible(true);

                    // Close the difficulty window
                    dispose();
                } // end of actionPerformed
            }); // end of addActionListener
            add(normalButton);

            JButton hardButton = new JButton("Hard");
            hardButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    hardMode = true;

                    // Show character selection window
                    CharacterSelectionWindow characterWindow = new CharacterSelectionWindow(1);
                    characterWindow.setVisible(true);

                    // Close the difficulty window
                    dispose();
                } // end of actionPerformed
            }); // end of addActionListener
            add(hardButton);
        } // end of DifficultyWindow
    } // end of DifficultyWindow

    // Inner class for character selection window
    private class CharacterSelectionWindow extends JFrame {
        int i;

        public CharacterSelectionWindow(int i) {
            this.i = i;

            setTitle("Choose Your Character");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(1080, 720);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 1));

            JLabel chooseCharacterLabel = new JLabel();
            chooseCharacterLabel.setText("Player" + i +" Choose character:");
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

                            Main.playerList.add(CharacterSelection.character(character, "Player " + i)); // Adds the player to the player list

                            LogMethods.insertLog(character, i, false); // Invokes insertLog for player selection

                            if (i < human) {
                                // If there are more human players, show the next selection window
                                CharacterSelectionWindow nextCharacterWindow = new CharacterSelectionWindow(i + 1);
                                nextCharacterWindow.setVisible(true);
                            } else {
                            // If all human players have selected, notify Main
                                synchronized (lock) {
                                    allCharactersSelected = true;
                                    lock.notify();
                                } // end of synchronized
                            } // end if else conditions

                            // Close the character selection window
                            dispose();
                        } // end if condition
                    } // end of actionPerformed
                }); // end of addActionListener
                add(characterButton);
            } // end for loop
        } // end of CharacterSelectionWindow
    } // end of CharacterSelectionWindow

    private class ResultWindow extends JFrame {
        public ResultWindow(String result) {
            setTitle("Battle Results");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(1080, 720);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());
    
            JTextArea resultTextArea = new JTextArea(result);
            resultTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(resultTextArea);
            add(scrollPane, BorderLayout.CENTER);
    
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            int option = JOptionPane.showConfirmDialog(null,
                                "Do you want to save the result in a log?",
                                "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.NO_OPTION) {
                // Show confirmation message
                JOptionPane.showMessageDialog(null, "Result won't be saved in a file. ");

                if((new File("Log_" + LogMethods.currentDate() + ".txt")).delete()) {
                    System.out.println("File deleted");
                } else {
                    System.out.println("Failed to delete the file.");
                } // end if else condition
            } else {
                // Show confirmation message
                JOptionPane.showMessageDialog(null, "Result saved in a file.");
            } // end if else conditions
            
            // Set a smaller size for the close button
            closeButton.setPreferredSize(new Dimension(80, 30));
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(closeButton);
            add(buttonPanel, BorderLayout.SOUTH);
        } // end of ResultWindow
    } // end of ResultWindow

    public void showResultWindow(String result) {
        ResultWindow resultWindow = new ResultWindow(result);
        resultWindow.setVisible(true);
    }
} // end of WindowFile