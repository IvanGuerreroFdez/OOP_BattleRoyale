import java.io.*;
import java.util.*;

/* import javax.swing.JDialog;
import javax.swing.JFrame; */

public class Main {
    static WindowFile ventana = new WindowFile();

    public static Player[] characterList = new Player[27]; // List of DEFAULT characters outside the file
    public static ArrayList<Player> playerList = new ArrayList<Player>(); // size 0 will be changed from WindowFile when the user gives the input

    //public static StringBuilder strbuild = new StringBuilder();

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
                playerList.add(CharacterSelection.comCharacter(ventana.ai)[i - human]);
                LogMethods.insertLog(playerList.get(i).character, i - human, true);
            } // end for loop

            ventana.dispose(); // Closes the window

            simulateBattleRoyale(); // Invoke the method to simulate the game

        } catch(Exception e) {
            System.out.println("An error occurred.");
        } finally {
            System.out.println("Main finished.");
        } // end of try, catch, finally
    } // end of main

    private static void simulateBattleRoyale() {
        StringBuilder resultStringBuilder = new StringBuilder();

        while(playerList.size() > 1) {
            printCurrentPlayers();
            resultStringBuilder.append(getCurrentPlayersInfo());
    
            List<Player> playersToRemove = new ArrayList<>();
    
            Iterator<Player> iterator = playerList.iterator();
            while (iterator.hasNext()) {
                Player player1 = iterator.next();
    
                if (iterator.hasNext()) {
                    Player player2 = iterator.next();
    
                    int result = Game.turn(player1, player2, ventana.hardMode);
    
                    if (result == 1) {
                        // Player 1 wins, mark player 2 for removal
                        playersToRemove.add(player2);
                        //resultStringBuilder.append(Game.strbuild.toString());  // Agrega el resultado del enfrentamiento
                    } else if (result == 2) {
                        // Player 2 wins, mark player 1 for removal
                        playersToRemove.add(player1);
                        //resultStringBuilder.append(Game.strbuild.toString());  // Agrega el resultado del enfrentamiento
                    }
                } else {
                    // Impar, el jugador pasa automáticamente a la siguiente ronda
                } // end if else condition

                //resultStringBuilder.append(Game.strbuild.toString());
            } // end while loop
            
            // Eliminar jugadores marcados para la eliminación
            playerList.removeAll(playersToRemove);
            resultStringBuilder.append(Game.strbuild.toString());
            Game.strbuild.setLength(0);
        } // end of simulateBattleRoyale
    
        // Queda un solo jugador, imprimir el ganador
        if (!playerList.isEmpty()) {

            System.out.println("Winner: " + playerList.get(0).toString());
            resultStringBuilder.append("Winner: " + playerList.get(0).toString() + "\n");
            LogMethods.insertLog(playerList.get(0), 0); // Invoke insertLog for winner
            //LogMethods.insertLog(playerList.get(0));
        } else {
            System.out.println("No winner. Error in simulation.");
            resultStringBuilder.append("No winner. Error in simulation.\n");
        } // end if else condition
        ventana.showResultWindow(resultStringBuilder.toString());
    } // end if simulateBattleRoyale
    
    private static void printCurrentPlayers() {
        System.out.println("Current players:");
        Iterator<Player> iterator = playerList.iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            System.out.printf("%s - HP: %d\n", player.toString(), player.currentHP);
    
            if (player.currentHP <= 0) {
                iterator.remove(); // Eliminar jugadores con HP <= 0
                //System.out.println(player.character + " fainted!");
            } // end if condition
        } // end while loop
        System.out.println();
    } // end of printCurrentPlayers

    private static String getCurrentPlayersInfo() {
        StringBuilder currentPlayersInfo = new StringBuilder("Current players:\n");
    
        for (Player player : playerList) {
            currentPlayersInfo.append(player.toString())
                    .append(" - HP: ")
                    .append(player.currentHP)
                    .append("\n");
    
            if (player.currentHP <= 0) {
                currentPlayersInfo.append(player.character).append(" fainted!\n");
            } // end if condition
        } // end for loop
    
        currentPlayersInfo.append("\n");
    
        return currentPlayersInfo.toString();
    } // end of getCurrentPlayersInfo
} // end of Main