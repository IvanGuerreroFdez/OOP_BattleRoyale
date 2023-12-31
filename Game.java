//package ProjFin.OOP_BattleRoyale;

import java.lang.Math;
import java.io.File;
import java.util.Random;

public class Game {
    public static long damageCalculator(Player[] players, int attacker, int receiver) { // Calculates the damage of a move
        Random rn = new Random();
        double random = rn.nextDouble(1 - 0.85) + 0.85;
        int atk, def;
        double type = 1.0;

        if(players[attacker].SpAttacker) {
            atk = players[attacker].SpA;
        } else {
            atk = players[attacker].Atk;
        } // end if else conditions

        if(players[receiver].SpD > players[receiver].Def) {
            def = players[receiver].SpD;
        } else {
            def = players[receiver].Def;
        } // end if else conditions

        if(players[attacker].type == players[receiver].type) { // Same type --> not very effective
            type = 0.5;
        } else {
            if(players[attacker].type == "Grass") {
                if(players[receiver].type == "Fire") { // Super effective
                    type = 2;
                } else if(players[receiver].type == "Water") { // Not very effective
                    type = 0.5;
                } // end if else if conditions
            } else if(players[attacker].type == "Fire") {
                if(players[receiver].type == "Grass") { // Super effective
                    type = 2;
                } else if(players[receiver].type == "Water") { // Not very effective
                    type = 0.5;
                } // end if else if conditions
            } else if(players[attacker].type == "Water") {
                if(players[receiver].type == "Fire") { // Super effective
                    type = 2;
                } else if(players[receiver].type == "Grass") { // Not very effective
                    type = 0.5;
                } // end if else if conditions
            } // end if, else if x2 conditions
        } // end if else conditions

        return Math.round(((((2 * 50 / 5 + 2) * players[attacker].movePower * atk / def) / 50 + 2) * random * 1.5 * type));
    } // end of damageCalculator

    public static int turn(Player[] players, int turn, File f) {
        // From the array of players select two
        Random rn = new Random();
        int randP1 = rn.nextInt(27 - 1) + 1;
        int randP2 = rn.nextInt(27 - 1) + 1;

        // Compares if some of the players is human (checking names)
        if(players[randP1].playerName != null && players[randP2].playerName != null) { // Both players are human
            System.out.printf("%s (%s) vs. %s (%s).\n", players[randP1].playerName, players[randP1].character, players[randP2].playerName, players[randP2].character);
            System.out.println("BATTLE BEGIN!");
        } else if(players[randP1].playerName != null && players[randP2].playerName == null) { // 1st is human, 2nd is not
            System.out.printf("%s (%s) vs. %s.\n", players[randP1].playerName, players[randP1].character, players[randP2].character);
            System.out.println("BATTLE BEGIN!");
        } else if(players[randP1].playerName == null && players[randP2].playerName != null) { // 1st is not human, 2nd is
            System.out.printf("%s vs. %s (%s).\n", players[randP1].character, players[randP2].playerName, players[randP2].character);
            System.out.println("BATTLE BEGIN!");
        } else {
            System.out.println(players[randP1].character + " vs. " + players[randP2].character + ".");
            System.out.println("BATTLE BEGIN!");
        } // end if, else if x2, else condition

        int damage;

        // Compares the speed
        if(players[randP1].Spe > players[randP2].Spe) { // 1st is faster than 2nd
            System.out.println(players[randP1].character + " used " + players[randP1].moveName);

            damage = (int) damageCalculator(players, randP1, randP2); // Invokes damageCalculator and stores the result in damage (avoids multiple invocation)
            players[randP2].currentHP -= damage; // Damage is substracted from current HP

            System.out.println("(" + players[randP2].character + " lost " + damage * 100 / players[randP2].HP + "% of its health!)");
            
            if(players[randP2].currentHP < 0) { // If the character that recieved the damage has fainted
                // Compares if the 2nd player is human (checking names) to show the corresponding message
                if(players[randP2].playerName != null) {
                    System.out.printf("%s (%s) fainted!", players[randP2].playerName, players[randP2].character);
                } else {
                    System.out.println(players[randP2].character + "fainted!");
                } // end if else condition

                // Order array players
                
                return 1; // Returns 1 if first player wins
            } else { // If not, 2nd player turn
                System.out.println(players[randP2].character + " used " + players[randP2].moveName);

                damage = (int) damageCalculator(players, randP2, randP1); // Invokes damageCalculator and stores the result in damage (avoids multiple invocation)
                players[randP2].currentHP -= damage; // Damage is substracted from current HP

                System.out.println("(" + players[randP1].character + " lost " + damage * 100 / players[randP1].HP + "% of its health!)");

                if(players[randP1].currentHP < 0) { // If the character that recieved the damage has fainted
                    // Compares if the 1st player is human (checking names) to show the corresponding message
                    if(players[randP1].playerName != null) {
                        System.out.printf("%s (%s) fainted!", players[randP1].playerName, players[randP1].character);
                    } else {
                        System.out.println(players[randP1].character + "fainted!");
                    } // end if else condition

                    // Order array players
                    
                    return 2; // Returns 2 if second player wins
                } // end if condition
            } // end if else conditions
        } else { // 

        } // end if else conditions

        return 0; // Returns 0 if draw
    } // end of turn
} // end of Game