package pigeonsquare.pigeons;

import pigeonsquare.*;
import pigeonsquare.utils.Position;

/**
 * Classe Pigeon
 *
 */
public class Pigeon extends ElementMobile {

    private static final int distanceDangerMin = Params.distanceDangerMin;

    /**
     * Définir une direction suivant un objectif à déterminer
     *
     * @return la direction calculée
     */
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

    /**
     * Actions à réaliser lorsque le pigeon est sur son objectif
     *
     */
    public void surObjectif(){
        Nourriture nourriture = (Nourriture)elementObjectif;

        if(nourriture.getFrais()) nourriture.manger();
        stop();
    }

}
