package ProjFin.OOP_BattleRoyale;
public class Player {
    public String character, type;
    public int HP, Atk, Def, SpA, SpD, Spe, currentHP; // Health Points, Attack, Defense, Special Attack, Special Defense, Speed
    public String moveName;
    public int movePower, priority;
    public boolean SpAttacker;

    public Player(String character, String type, int HP, int Atk, int Def, int SpA, int SpD, 
        int Spe, String moveName, int movePower, int priority, boolean SpAttacker) {
        this.character = character;
        this.type = type;
        this.HP = HP;
        this.Atk = Atk;
        this.Def = Def;
        this.SpA = SpA;
        this.SpD = SpD;
        this.Spe = Spe;
        this.moveName = moveName;
        this.movePower = movePower;
        this.priority = priority;
        this.SpAttacker = SpAttacker;
        this.currentHP = HP;
    } // end of Player
} // end of Player