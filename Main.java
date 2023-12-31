import java.io.*;
import java.util.*;

public class Main {
    public static void main(String [] args) {
        try {
            System.out.println("Program started.");

            File characterData = new File("CharacterData.csv");

            WindowFile ventana = new WindowFile();
            ventana.setVisible(true);

            // ABRIR LOG.TXT
            // CREAR ARRAY PLAYERS
            
            // Importar numero de jugadores desde la ventana (imagino que ventana.human y ventana.ai)
            Player[] players = new Player[ventana.human + ventana.ai];

            int i = 0;
            for(; i < ventana.human; i++) {
                // New character selection window
                // Assign to array
                players[i] = new Player(/* insert data here */);
            } // end for loop

            for(; i < ventana.human + ventana.ai; i++) { // Selects random for 
                
            } // end for loop

            // PREFERIBLE INVOCAR LAS FUNCIONES DESDE AQUI

            // Dejar esto comentado para pruebas (comentar con shift + alt + A)
            // Cambiar para hacer pruebas a eleccion
            Player j2 = new Player("Greninja", "Kaaxerd", "Water", 72, 95, 67, 103, 71, 122, "Water Shuriken", 75, +1);
            Player j1 = new Player("Infernape", null, "Fire", 76, 104, 71, 104, 71, 108, "Flare Blitz", 120, 0);
            Game.turn(j1, j2);

        } catch(Exception e) {
            System.out.println("An error occurred.");
        } finally {
            System.out.println("Program finished.");
        } // end of try, catch, finally
    } // end of main
} // end of Main