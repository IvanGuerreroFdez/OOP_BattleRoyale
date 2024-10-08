import java.lang.Math;
//import java.io.*;
import java.util.*; //Checked

public class Game {
    public static StringBuilder strbuild = new StringBuilder();
    public static int damageCalculator(Player attacker, Player receiver, boolean hard) { // Calculates the damage of a move
        //StringBuilder resultStringBuilder = new StringBuilder();
        Random rn = new Random();
        double random = rn.nextDouble(1 - 0.85) + 0.85; // Random number between 0.85 and 1
        int atk, def;
        double type = 1.0;
        double critical = 1;

        if(attacker.SpA > attacker.Atk) { // Checks if attacker is a special attacker
            atk = attacker.SpA;
            def = receiver.SpD; // Special defense is used in calcs
        } else { // If physical attacker
            atk = attacker.Atk;
            def = receiver.Def; // Physical defense is used in calcs
        } // end if else conditions

        // Checks the type of both
        if(attacker.type.compareTo(receiver.type) == 0) { // Same type --> not very effective
            type = 0.5;
        } else {
            if(attacker.type.compareTo("Grass") == 0) {
                if(receiver.type.compareTo("Water") == 0) { // Super effective
                    type = 2;
                } else if(receiver.type.compareTo("Fire") == 0) { // Not very effective
                    type = 0.5;
                } // end if else if conditions
            } else if(attacker.type.compareTo("Fire") == 0) {
                if(receiver.type.compareTo("Grass") == 0) { // Super effective
                    type = 2;
                } else if(receiver.type.compareTo("Water") == 0) { // Not very effective
                    type = 0.5;
                } // end if else if conditions
            } else if(attacker.type.compareTo("Water") == 0) {
                if(receiver.type.compareTo("Fire") == 0) { // Super effective
                    type = 2;
                } else if(receiver.type.compareTo("Grass") == 0) { // Not very effective
                    type = 0.5;
                } // end if else if conditions
            } else {
                type = 1;
            } // end if, else if x2, else conditions
        } // end if else conditions

        if(hard == true) { // If it is hard mode, critical hits will be taken in account
            int critRand = rn.nextInt(2);
            //System.out.println(critRand);

            if(critRand == 1) {
                critical = 1.5;
            } // end if condition
        } // end if condition

        int damage = (int) Math.round(((((2 * 50 / 5 + 2) * attacker.movePower * atk / def) / 50 + 2) * random * 1.5 * type * critical));

        if(type == 2) {
            System.out.println("It's super effective!");
            //Main.strbuild.append("It's super effective!\n");
            strbuild.append("It's super effective!\n");
        } else if(type == 0.5) {
            System.out.println("It's not very effective...");
            //Main.strbuild.append("It's not very effective...\n");
            strbuild.append("It's not very effective...\n");
        } // end if, else if conditions

        if(critical == 1.5) {
            System.out.println("A critical hit!");
            //Main.strbuild.append("A critical hit!\n");
            strbuild.append("A critical hit!\n");
        } // end if condition

        System.out.printf("(%s lost %d%% of its health!)\n", receiver.toString(), damage * 100 / receiver.HP);
        strbuild.append("(" + receiver.toString() + " lost " + damage * 100 / receiver.HP + "% of its health!)\n");
        LogMethods.insertLog(attacker, receiver, type, critical, damage); // Invoke insertLog for damage
        
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

    public static int turn(Player p1, Player p2, boolean hard) {
        try {
            System.out.println(p1.toString() + " vs. " + p2.toString());
            System.out.println("BATTLE BEGIN!");
            strbuild.append(p1.toString() + " vs. " + p2.toString() + "\nBATTLE BEGIN!\n");

            LogMethods.insertLog(p1, p2); // Invoke insertLog for battle start

            int damage;

            if(handleTurnOrder(p1, p2) == 1) { // p1 goes first
                System.out.println(p1.toString() + " used " + p1.moveName + ".");
                strbuild.append(p1.toString() + " used " + p1.moveName + ".\n");

                damage = damageCalculator(p1, p2, hard); // Invokes damageCalculator and stores the result in damage (avoids multiple invocation)
                p2.currentHP -= damage; // Damage is substracted from current HP
                
                if(p2.currentHP < 0) { // If the character that recieved the damage has fainted
                    System.out.println(p2.toString() + " fainted!");
                    //Main.strbuild.append(p2.toString() + " fainted!\n");
                    strbuild.append(p2.toString() + " fainted!\n");
                    LogMethods.insertLog(p2); // Invoke insertLog for faint
                    
                    return 1; // Returns 1 if first player wins
                } else { // If not, 2nd player turn
                    System.out.println(p2.toString() + " used " + p2.moveName + ".");
                    strbuild.append(p2.toString() + " used " + p2.moveName + ".\n");

                    damage = damageCalculator(p2, p1, hard); // Invokes damageCalculator and stores the result in damage (avoids multiple invocation)
                    p1.currentHP -= damage; // Damage is substracted from current HP

                    if(p1.currentHP < 0) { // If the character that recieved the damage has fainted
                        System.out.println(p1.toString() + " fainted!");
                        //Main.strbuild.append(p1.toString() + " fainted!\n");
                        strbuild.append(p1.toString() + " fainted!\n");
                        LogMethods.insertLog(p1); // Invoke insertLog for faint
                        
                        return 2; // Returns 2 if second player wins
                    } // end if condition
                } // end if else conditions
            } else { // p2 goes first
                System.out.println(p2.toString() + " used " + p2.moveName + ".");
                //Main.strbuild.append(p2.toString() + " used " + p2.moveName + ".\n");
                strbuild.append(p2.toString() + " used " + p2.moveName + ".\n");

                damage = damageCalculator(p2, p1, hard); // Invokes damageCalculator and stores the result in damage (avoids multiple invocation)
                p1.currentHP -= damage; // Damage is substracted from current HP
                
                if(p1.currentHP < 0) { // If the character that recieved the damage has fainted
                    System.out.println(p1.toString() + " fainted!");
                    strbuild.append(p1.toString() + " fainted!\n");
                    LogMethods.insertLog(p1); // Invoke insertLog for faint

                    return 2; // Returns 2 if second player wins
                } else { // If not, 2nd player turn
                    System.out.println(p1.character + " used " + p1.moveName + ".");
                    strbuild.append(p1.character + " used " + p1.moveName + ".\n");

                    damage = damageCalculator(p1, p2, hard); // Invokes damageCalculator and stores the result in damage (avoids multiple invocation)
                    p2.currentHP -= damage; // Damage is substracted from current HP

                    if(p2.currentHP < 0) { // If the character that recieved the damage has fainted
                        System.out.println(p2.toString() + " fainted!");
                        strbuild.append(p2.toString() + " fainted!\n");
                        LogMethods.insertLog(p2); // Invoke insertLog for faint

                        return 1; // Returns 1 if first player wins
                    } // end if condition
                } // end if else conditions
            } // end if else conditions
            
            return 0; // Returns 0 if draw
        } catch(Exception e) {
            System.out.println("There has been an error reading the file");
        } // end of try, catch

        return -1; // Returns -1 if error
    } // end of turn
} // end of Game