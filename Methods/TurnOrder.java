package Methods;
import Player; // quiero importar la clase player desde la carpeta padre

public class TurnOrder {
    public void turnOrder(Player[] players) {
        for(int i = 0; i < players.length(); i++) {
            for(int j = 1; j < players.length; j++) {
                if(players[i].Spe < players[j].Spe) {
                    Player aux = players[i];
                    players[i] = players[j];
                    players[j] = aux;
                } // end if condition
            } // end for loop
        } // end for loop
    } // end of turnOrder
} // end of TurnOrder