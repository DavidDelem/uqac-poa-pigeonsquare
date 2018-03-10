package pigeonsquare.pigeons;

import javafx.geometry.Pos;
import pigeonsquare.*;
import pigeonsquare.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Pigeon extends ElementMobile {

    private static final int distanceDangerMin = 150;

    public Pigeon() {
    }

    public Position calculerDirection() {

        Position direction = null;

        Nourriture nourritureProche = Square.getInstance().nourriturePlusProche(this);
        Caillou caillouProche = Square.getInstance().caillouProche(this, this.distanceDangerMin);

        if(caillouProche != null){

            direction = new Position(
                    caillouProche.getPosition().x-this.position.x,
                    caillouProche.getPosition().y-this.position.y
            );
            direction.x *= -1;
            direction.y *= -1;
            direction.normalisation();
            return direction;
        }

        if(nourritureProche != null && !this.position.proche(nourritureProche.getPosition())){

            this.elementObjectif = nourritureProche;

            direction = new Position(
                    nourritureProche.getPosition().x-this.position.x,
                    nourritureProche.getPosition().y-this.position.y
            );
            direction.normalisation();
            return direction;
        }

        return null;
    }

    public void surObjectif(){
        Nourriture nourriture = (Nourriture)elementObjectif;

        if(nourriture.getFrais()){
            //Square.getInstance().supprimerNourriture((Nourriture)elementObjectif);
            nourriture.manger();
        }
        stop();
    }

}
