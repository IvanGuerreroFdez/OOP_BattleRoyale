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

            w.write(attacker.toString() + " used " + attacker.moveName + ".\n");

            if(type == 2) {
                w.write("It's super effective!\n");
            } else if(type == 0.5) {
                w.write("It's not very effective...\n");
            } // end if, else if  conditions

            if(critical == 1.5) {
                w.write("A critical hit!\n");
            } // end if condition

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

            w.write(fainted.toString() + " fainted!\n");

            w.close();
        } catch(Exception e) {
            System.out.println("An error occurred. :/");
        } // end try, catch
    } // end of insertLog

    // insertLog for winner
    public static void insertLog(Player winner, int aaaaaaaaa) {
        try {
            FileWriter w = new FileWriter("Log_" + currentDate() + ".txt", true);

            w.write(winner.toString() + " has won!\n");

            w.close();
        } catch(Exception e) {
            System.out.println("An error occurred. :/");
        } // end of try, catch
    } // end of insertLog
} // end of LogMethods