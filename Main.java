import java.io.*;
import java.util.*;

public class Main {
    public static void main(String [] args) {
        try {
            System.out.println("Program started.");

            File characterData = new File("CharacterData.csv");
            Scanner sc = new Scanner(characterData);

            WindowFile ventana = new WindowFile();
            ventana.setVisible(true);

            // ABRIR LOG.TXT

            Player[] characterList = new Player[27]; // List of characters outside the file
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
                System.out.println(ventana.characters[i]);
            } // end for loop

            sc.close();

            /* // Checks if characterList has been done correctly
            for(int i = 0; i < characterList.length; i++) {
                 System.out.println("Character " + i);
                    System.out.println(characterList[i].character);
                    System.out.println(characterList[i].type);
                    System.out.println(characterList[i].HP);
                    System.out.println(characterList[i].Atk);
                    System.out.println(characterList[i].Def);
                    System.out.println(characterList[i].SpA);
                    System.out.println(characterList[i].SpD);
                    System.out.println(characterList[i].Spe);
                    System.out.println(characterList[i].moveName);
                    System.out.println(characterList[i].movePower);
                    System.out.println(characterList[i].priority);
            } // end for loop */

            /* // Dejar esto comentado para pruebas (comentar con shift + alt + A)
            // Cambiar para hacer pruebas a eleccion
            Player j2 = new Player("Greninja", "Kaaxerd", "Water", 72, 95, 67, 103, 71, 122, "Water Shuriken", 75, +1);
            Player j1 = new Player("Infernape", null, "Fire", 76, 104, 71, 104, 71, 108, "Flare Blitz", 120, 0);
            Game.turn(j1, j2); */

        } catch(Exception e) {
            System.out.println("An error occurred.");
        } finally {
            System.out.println("Program finished.");
        } // end of try, catch, finally
    } // end of main
} // end of Main