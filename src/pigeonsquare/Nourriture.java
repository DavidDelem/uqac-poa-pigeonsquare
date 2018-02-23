package pigeonsquare;

import pigeonsquare.utils.Position;

public class Nourriture extends Element {

    public Nourriture(Position position) {
        this.position = position;
        this.chargerImage("ressources/nourriture.png");
    }
}