import java.util.Random;

public class CharacterSelection {
    // Method to assign the character to one player according to the selection
    public static Player character(String selection, String name) {
        for(int i = 0; i < Main.characterList.length; i++) {
            if(Main.characterList[i].character == selection) { // Detects if the selection coincides with a value from the characterList array
                return new Player(selection, name, Main.characterList[i].type, 
                Main.characterList[i].HP, Main.characterList[i].Atk, Main.characterList[i].Def, Main.characterList[i].SpA, Main.characterList[i].SpD, Main.characterList[i].Spe, 
                Main.characterList[i].moveName, Main.characterList[i].movePower, Main.characterList[i].priority);
            } // end if condition
        } // end for loop

        // This will never happen
        return new Player("Missingno", null, "BIRD/Normal", 0, 0, 0, 0, 0, 0, null, 0, 0);
    } // end of character

    // Method to assign characters to COM players
    public static Player[] comCharacter(int comPlayers) {
        Player[] aiPlayers = new Player[comPlayers];
        Random rn = new Random();
        int random = rn.nextInt(27);

        for(int i = 0; i < comPlayers; i++) {
            // array[i + humanPlayers] = Random character from .csv
            aiPlayers[i] = new Player(Main.characterList[random].character, null, Main.characterList[random].type, 
                Main.characterList[random].HP, Main.characterList[random].Atk, Main.characterList[random].Def, Main.characterList[random].SpA, Main.characterList[random].SpD, Main.characterList[random].Spe, 
                Main.characterList[random].moveName, Main.characterList[random].movePower, Main.characterList[random].priority);
        } // end if condition

        return aiPlayers;
    } // end of comCharacter
} // end of CharacterSelection