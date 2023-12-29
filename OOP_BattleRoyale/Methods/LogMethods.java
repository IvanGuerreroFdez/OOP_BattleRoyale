package OOP_BattleRoyale.Methods;
import java.io.*;

public class LogMethods {
    // insertLog for character selected
    public void insertLog(File log, int character, int player) {
        try {
            FileWriter w = new FileWriter(log);

            // 0: Soldier // 1: Archer // 2: Wizard
            switch(character) {
                case 0:
                    w.write("Player " + player + " selected Soldier.");
                    break;
                case 1:
                    w.write("Player " + player + " selected Archer.");
                    break;
                case 2:
                    w.write("Player " + player + " selected Wizard.");
                    break;
            } // end switch condition

            w.close();
        } catch(Exception e) {
            System.out.println("An error occurred. :/");
        } // end of try, catch
    } // end of insertLog

    // insertLog for start/end game
    public void insertLog(File log, boolean started) {
        try {
            FileWriter w = new FileWriter(log);

            // started means that the game has already started
            // that means that if the game has already started the game will finish
            if(started) {
                w.write("Game ended.");
            } else {
                w.write("Game started.");
            } // end if else conditions

            w.close();
        } catch(Exception e) {
            System.out.println("An error occurred. :/");
        } // end of try, catch
    } // end of insertLog
    
    // insertLog for turns
    /* public void insertLog(File log, int action) {
        try {
            FileWriter w = new FileWriter(log);

            // 0: protected
            // 1: attacked

            w.close();
        } catch(Exception e) {
            System.out.println("An error occurred. :/");
        } // end of try, catch
    } // end of insertLog */
} // end of LogMethods