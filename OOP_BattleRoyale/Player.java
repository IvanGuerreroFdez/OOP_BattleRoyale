package OOP_BattleRoyale;

public class Player {
    public String character;
    public int HP, Atk, Def, SpA, SpD, Spe;
    public int lastAction = 0; // 0: protected, 1: attacked, 0 by default
    // Health Points, Attack, Defense, Special Attack, Special Defense, Speed

    public Player(String character, int HP, int Atk, int Def, int SpA, int SpD, int Spe) {
        this.character = character;
        this.HP = HP;
        this.Atk = Atk;
        this.Def = Def;
        this.SpA = SpA;
        this.SpD = SpD;
        this.Spe = Spe;
    } // end of Player
} // end of Player