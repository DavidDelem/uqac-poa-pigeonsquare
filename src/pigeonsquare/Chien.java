package pigeonsquare;

import pigeonsquare.utils.Position;

public class Chien extends ElementMobile {

    public Chien(Position position) {
        this.position = position;
        this.chargerImage("ressources/chien.png");
    }

    public Position determinerProchainePosition() {
        return null;
    }

    public boolean surObjectif(){
        return  false;
    }

}
