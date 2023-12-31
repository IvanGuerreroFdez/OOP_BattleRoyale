//package ProjFin.OOP_BattleRoyale;
public class Player {
    public String character, playerName, type;
    public int HP, Atk, Def, SpA, SpD, Spe, currentHP; // Health Points, Attack, Defense, Special Attack, Special Defense, Speed
    public String moveName;
    public int movePower, priority;

    public Player(String character, String playerName, String type, 
        int HP, int Atk, int Def, int SpA, int SpD, int Spe, 
        String moveName, int movePower, int priority) {
        this.character = character;
        this.playerName = playerName;
        this.type = type;

        // Makes the corresponding calculations to change base stats from the .csv into real stats at level 50
        this.HP = ((2 * HP * 50) / 100) + 50 + 10; // (((2 * base) * lvl) / 100) + lvl + 10
        this.Atk = ((2 * Atk) * 50 / 100) + 5; // ((2 * base) * lvl / 100) + 5
        this.Def = ((2 * Def) * 50 / 100) + 5; // ((2 * base) * lvl / 100) + 5
        this.SpA = ((2 * SpA) * 50 / 100) + 5; // ((2 * base) * lvl / 100) + 5
        this.SpD = ((2 * SpD) * 50 / 100) + 5; // ((2 * base) * lvl / 100) + 5
        this.Spe = ((2 * Spe) * 50 / 100) + 5; // ((2 * base) * lvl / 100) + 5

        this.moveName = moveName;
        this.movePower = movePower;
        this.priority = priority;
        this.currentHP = this.HP;
    } // end of Player
} // end of Player