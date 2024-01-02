import java.lang.Math;
import java.io.*;
import java.util.*;

public class Game {
    public static int damageCalculator(Player attacker, Player receiver) { // Calculates the damage of a move
        Random rn = new Random();
        double random = rn.nextDouble(1 - 0.85) + 0.85;
        int atk, def;
        double type = 1.0;

        if(attacker.SpA > attacker.Atk) { // Checks if attacker is a special attacker
            atk = attacker.SpA;
            def = receiver.SpD; // Special defense is used in calcs
        } else { // If physical attacker
            atk = attacker.Atk;
            def = receiver.Def; // Physical defense is used in calcs
        } // end if else conditions

        // Checks the type of both
        if(attacker.type == receiver.type) { // Same type --> not very effective
            type = 0.5;
        } else {
            if(attacker.type == "Grass") {
                if(receiver.type == "Fire") { // Super effective
                    type = 2;
                } else if(receiver.type == "Water") { // Not very effective
                    type = 0.5;
                } // end if else if conditions
            } else if(attacker.type == "Fire") {
                if(receiver.type == "Grass") { // Super effective
                    type = 2;
                } else if(receiver.type == "Water") { // Not very effective
                    type = 0.5;
                } // end if else if conditions
            } else if(attacker.type == "Water") {
                if(receiver.type == "Fire") { // Super effective
                    type = 2;
                } else if(receiver.type == "Grass") { // Not very effective
                    type = 0.5;
                } // end if else if conditions
            } else {
                type = 1;
            } // end if, else if x2, else conditions
        } // end if else conditions

        int damage = (int) Math.round(((((2 * 50 / 5 + 2) * attacker.movePower * atk / def) / 50 + 2) * random * 1.5 * type));

        if(type == 2) {
            System.out.println("It's super effective!");
        } else if(type == 0.5) {
            System.out.println("Its not very effective...");
        } // end if, else if conditions

        // Compares if the receiver is human (checking names) to show the corresponding message
        if(receiver.playerName != null) {
            System.out.printf("(%s (%s) lost %d%% of its health!)\n", receiver.playerName, receiver.character, damage * 100 / receiver.HP);
        } else {
            System.out.printf("(%s lost %d%% of its health!)\n", receiver.character, damage * 100 / receiver.HP);
        } // end if else condition

        return damage;
    } // end of damageCalculator

    public static int handleTurnOrder(Player p1, Player p2) {
        if(p1.priority > p2.priority) {
            return 1; // Returns 1 if p1 goes first
        } else if(p1.priority < p2.priority) {
            return 0; // Returns 0 if p2 goes first
        } else {
            if(p1.Spe > p2.Spe) {
                return 1; // Returns 1 if p1 goes first
            } else {
                return 0; // Returns 0 if p2 goes first
            } // end if else conditions
        } // end if, else if, else conditions
    } // end of handleTurnOrder

    public static int turn(Player p1, Player p2/* , File log */) {
        try {
            //FileWriter w = new FileWriter(log); // Write in the log file

            // Compares if some of the players is human (checking names)
            if(p1.playerName != null && p2.playerName != null) { // Both players are human
                System.out.printf("%s (%s) vs. %s (%s).\n", p1.playerName, p1.character, p2.playerName, p2.character);
                // w.write(p1.playerName + "(" + p1.character + ") vs " + p1.playerName + "(" + p1.character + ").\n");
                System.out.println("BATTLE BEGIN!");
            } else if(p1.playerName != null && p2.playerName == null) { // 1st is human, 2nd is not
                System.out.printf("%s (%s) vs. %s.\n", p1.playerName, p1.character, p2.character);
                // w.write(p1.playerName + "(" + p1.character + ") vs " +  p1.character + ".\n");
                System.out.println("BATTLE BEGIN!");
            } else if(p1.playerName == null && p2.playerName != null) { // 1st is not human, 2nd is
                System.out.printf("%s vs. %s (%s).\n", p1.character, p2.playerName, p2.character);
                // w.write(p1.character + " vs " + p1.playerName + "(" + p1.character + ").\n");
                System.out.println("BATTLE BEGIN!");
            } else {
                System.out.println(p1.character + " vs. " + p2.character + ".");
                // w.write(p1.character + " vs " + p1.character + ".\n");
                System.out.println("BATTLE BEGIN!");
            } // end if, else if x2, else condition

            int damage;

            if(handleTurnOrder(p1, p2) == 1) { // p1 goes first
                System.out.println(p1.character + " used " + p1.moveName + ".");
                // w.write(p1.character + " used " + p1.moveName + ".\n");

                damage = damageCalculator(p1, p2); // Invokes damageCalculator and stores the result in damage (avoids multiple invocation)
                p2.currentHP -= damage; // Damage is substracted from current HP
                
                if(p2.currentHP < 0) { // If the character that recieved the damage has fainted
                    // Compares if the 2nd player is human (checking names) to show the corresponding message
                    if(p2.playerName != null) {
                        System.out.printf("%s (%s) fainted!\n", p2.playerName, p2.character);
                    } else {
                        System.out.println(p2.character + " fainted!");
                    } // end if else condition
                    
                    // w.close();
                    return 1; // Returns 1 if first player wins
                } else { // If not, 2nd player turn
                    System.out.println(p2.character + " used " + p2.moveName);

                    damage = damageCalculator(p2, p1); // Invokes damageCalculator and stores the result in damage (avoids multiple invocation)
                    p1.currentHP -= damage; // Damage is substracted from current HP

                    if(p1.currentHP < 0) { // If the character that recieved the damage has fainted
                        // Compares if the 1st player is human (checking names) to show the corresponding message
                        if(p1.playerName != null) {
                            System.out.printf("%s (%s) fainted!\n", p1.playerName, p1.character);
                        } else {
                            System.out.println(p1.character + " fainted!");
                        } // end if else condition
                        
                        // w.close();
                        return 2; // Returns 2 if second player wins
                    } // end if condition
                } // end if else conditions
            } else { // p2 goes first
                System.out.println(p2.character + " used " + p2.moveName);

                damage = damageCalculator(p2, p1); // Invokes damageCalculator and stores the result in damage (avoids multiple invocation)
                p1.currentHP -= damage; // Damage is substracted from current HP
                
                if(p1.currentHP < 0) { // If the character that recieved the damage has fainted
                    // Compares if the 2nd player is human (checking names) to show the corresponding message
                    if(p1.playerName != null) {
                        System.out.printf("%s (%s) fainted!", p1.playerName, p1.character);
                    } else {
                        System.out.println(p1.character + "fainted!");
                    } // end if else condition
                    
                    // w.close();
                    return 2; // Returns 2 if second player wins
                } else { // If not, 2nd player turn
                    System.out.println(p1.character + " used " + p1.moveName);

                    damage = damageCalculator(p1, p2); // Invokes damageCalculator and stores the result in damage (avoids multiple invocation)
                    p2.currentHP -= damage; // Damage is substracted from current HP

                    System.out.println("(" + p2.character + " lost " + damage * 100 / p2.HP + "% of its health!)");

                    if(p2.currentHP < 0) { // If the character that recieved the damage has fainted
                        // Compares if the 1st player is human (checking names) to show the corresponding message
                        if(p2.playerName != null) {
                            System.out.printf("%s (%s) fainted!", p2.playerName, p2.character);
                        } else {
                            System.out.println(p2.character + "fainted!");
                        } // end if else condition
                        
                        // w.close();
                        return 1; // Returns 1 if first player wins
                    } // end if condition
                } // end if else conditions
            } // end if else conditions
            
            // w.close();
            return 0; // Returns 0 if draw
        } catch(Exception e) {
            System.out.println("There has been an error reading the file");
        } // end of try, catch

        return -1; // Returns -1 if error
    } // end of turn
} // end of Game