import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class LogMethods {
    public static String currentDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);
        
        return formattedDate;
    } // end of currentDate

    // insertLog for character selected
    public static void insertLog(String character, int i, boolean ai) {
        try {
            FileWriter w = new FileWriter("Log_" + currentDate() + ".txt", true);

            if(ai) {
                w.write("AI " + i + " selected " + character + ".\n");
            } else {
                w.write("Player " + i + " selected " + character + ".\n");
            } // end if else conditions

            w.close();
        } catch(Exception e) {
            System.out.println("An error occurred. :/");
        } // end of try, catch
    } // end of insertLog
    
    // insertLog for battle start
    public static void insertLog(Player p1, Player p2) {
        try {
            FileWriter w = new FileWriter("Log_" + currentDate() + ".txt", true);

            /* if(p1.playerName != null && p2.playerName != null) { // Both players are human
                w.write(p1.playerName + " (" + p1.character + ") vs. " + p2.playerName + " (" + p2.character + ").\n");
            } else if(p1.playerName != null && p2.playerName == null) { // 1st is human, 2nd is not
                w.write(p1.playerName + " (" + p1.character + ") vs. " + p2.character + ".\n");
            } else if(p1.playerName == null && p2.playerName != null) { // 1st is not human, 2nd is
                w.write(p1.character + " vs. " + p2.playerName + " (" + p2.character + ").\n");
            } else {
                w.write(p1.character + " vs. " + p2.character + ".\n");
            } // end if, else if x2, else condition */

            w.write(p1.toString() + " vs. " + p2.toString());

            w.close();
        } catch(Exception e) {
            System.out.println("An error occurred. :/");
        } // end of try, catch
    } // end of insertLog

    // insertLog for damage
    public static void insertLog(Player attacker, Player receiver, double type, double critical, int damage) {
        try {
            FileWriter w = new FileWriter("Log_" + currentDate() + ".txt", true);

            /* if(attacker.playerName != null) {
                w.write(attacker.playerName + " (" + attacker.character + ") used " + attacker.moveName + ".\n");
            } else {
                w.write(attacker.character + " used " + attacker.moveName + ".\n");
            } // end if else conditions */

            w.write(attacker.toString() + " used " + attacker.moveName + ".\n");

            if(type == 2) {
                w.write("It's super effective!\n");
            } else if(type == 0.5) {
                w.write("It's not very effective...\n");
            } // end if, else if  conditions

            if(critical == 1.5) {
                w.write("A critical hit!\n");
            } // end if condition

            /* if(receiver.playerName != null) {
                w.write(receiver.playerName + " (" + receiver.character + ") lost " + (damage * 100 / receiver.HP) + "% of its health.\n");
            } else {
                w.write(receiver.character + " lost " + (damage * 100 / receiver.HP) + "% of its health.\n");
            } // end if else condition */

            w.write(receiver.toString() + " lost " + (damage * 100 / receiver.HP) + "% of its health.\n");

            w.close();
        } catch(Exception e) {
            System.out.println("An error occurred. :/");
        } // end of try, catch
    } // end of insertLog

    // insertLog for faint
    public static void insertLog(Player fainted) {
        try {
            FileWriter w = new FileWriter("Log_" + currentDate() + ".txt", true);

            /* if(fainted.playerName != null) {
                w.write(fainted.playerName + " (" + fainted.character + ") fainted!\n");
            } else {
                w.write(fainted.character + " fainted!\n");
            } // end if else condition */

            w.write(fainted.toString() + " fainted!\n");

            w.close();
        } catch(Exception e) {
            System.out.println("An error occurred. :/");
        } // end try, catch
    } // end of insertLog
} // end of LogMethods