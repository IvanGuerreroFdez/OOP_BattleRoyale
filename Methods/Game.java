package Methods;
import java.io.File;
import java.util.Random;

import Main; // esto en teoria funciona pero no quiere compilar
import Player; // esto en teoria funciona pero no quiere compilar

public class Game {
    public static double damageCalculator(Player[] players, int attacker, int receiver) {
        Random rn = new Random();
        double random = rn.nextDouble(1 - 0.85) + 0.85;
        int atk, def;
        double type;

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

        return (((2 * 50 / 5 + 2) * players[attacker] * atk / def) / 50 + 2) * random * 1.5 * type;
    } // end of damageCalculator

    public static Player[] turn(Player[] players, int turn, File f) {
        // From the array of players select two
        Random rn = new Random();
        int randP1 = rn.nextInt(27 - 1) + 1;
        int randP2 = rn.nextInt(27 - 1) + 1;

        if(players[randP1].Spe > players[randP2].Spe) { // 2nd is faster than 1st
            System.out.println(players[randP1].character + " used " + players[randP1].moveName);
            System.out.println("(" + players[randP2].character + " lost " + 
                damageCalculator(players, randP1, randP2) * 100 / players[randP2].HP + "% of its health!)");
            
            if(damageCalculator(players, randP1, randP2) > players[randP2].HP) {
                
            } // end if condition
        } else { 

        } // end if else conditions
    } // end of turn
} // end of Game