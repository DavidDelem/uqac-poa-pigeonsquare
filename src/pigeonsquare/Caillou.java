package pigeonsquare;

import pigeonsquare.utils.Position;

public class Caillou extends ElementMobile {

    public Caillou(Position position) {
        this.position = position;
        this.chargerImage("ressources/chien.png");
    }

    public void determinerObjectif() {
    }

    public void surObjectif(){
       stop();
    }

}
