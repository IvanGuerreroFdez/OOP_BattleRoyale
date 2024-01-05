import java.io.*;
import java.util.*;

/* import javax.swing.JDialog;
import javax.swing.JFrame; */

public class Main {
    static WindowFile ventana = new WindowFile();

    public static Player[] characterList = new Player[27]; // List of DEFAULT characters outside the file
    public static ArrayList<Player> playerList = new ArrayList<Player>(); // size 0 will be changed from WindowFile when the user gives the input

    public static void main(String [] args) {
        try {
            System.out.println("Program started.");

            File characterData = new File("CharacterData.csv");
            Scanner sc = new Scanner(characterData);

            ventana.setVisible(true);

            while (ventana.getHuman() == 0 || ventana.getAi() == 0) {
                Thread.sleep(100);
            } // end while loop
            
            int human = ventana.getHuman();
            int ai = ventana.getAi();

            int k = 0;

            // Reads the contents of the csv and creates the character
            while(sc.hasNextLine()) {
                String line = sc.nextLine(); 
                String[] numbers = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"); // Splits if there is a comma

                if(numbers.length == 11) {
                    characterList[k] = new Player(numbers[0], null, numbers[1], Integer.parseInt(numbers[2]), 
                    Integer.parseInt(numbers[3]), Integer.parseInt(numbers[4]), Integer.parseInt(numbers[5]), 
                    Integer.parseInt(numbers[6]), Integer.parseInt(numbers[7]), numbers[8], 
                    Integer.parseInt(numbers[9]), Integer.parseInt(numbers[10]));
                } // end if condition

                k++;
            } // end while loop

            // Imports the names of the characters in the array of WindowFile
            for(int i = 0; i < 27; i++) {
                ventana.characters[i] = characterList[i].character;
                //System.out.println(ventana.characters[i]);
            } // end for loop

            sc.close();

            while(!ventana.allCharactersSelected()) {
                synchronized (ventana.getLock()) {
                    ventana.getLock().wait();
                } // end of synchronized
            } // end while loop
            System.out.println("All characters selected.");

            // Assigns the remaining AI players
            for(int i = human; i < human + ai; i++) {
                //playerList[i] = CharacterSelection.comCharacter(ventana.ai)[i - human];
                playerList.add(CharacterSelection.comCharacter(ventana.ai)[i - human]);
                LogMethods.insertLog(playerList.get(i).character, i - human, true);
            } // end for loop

            /* // Prints all characters on screen
            for(int i = 0; i < playerList.size(); i++) {
                System.out.println("playerList position " + i + " = " + playerList.get(i).character + " " + playerList.get(i).playerName);
            } // end for loop */

            ventana.dispose(); // Closes the window

            // Dejar esto comentado para pruebas (comentar con shift + alt + A)
            // Cambiar para hacer pruebas a eleccion
            Player j2 = new Player("Greninja", "Kaaxerd", "Water", 72, 95, 67, 103, 71, 122, "Water Shuriken", 75, +1);
            Player j1 = new Player("Infernape", null, "Fire", 76, 104, 71, 104, 71, 108, "Flare Blitz", 120, 0);
            System.out.println(ventana.hardMode);
            Game.turn(j1, j2, ventana.hardMode);

        } catch(Exception e) {
            System.out.println("An error occurred.");
        } finally {
            System.out.println("Main finished.");
        } // end of try, catch, finally
    } // end of main
} // end of Main