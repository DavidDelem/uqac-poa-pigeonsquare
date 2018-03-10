package pigeonsquare.pigeons;


import pigeonsquare.Params;
import pigeonsquare.utils.Position;

/**
 * Classe Ramier
 *
 */
public class Ramier extends Pigeon {

    public Ramier(Position position) {
        this.position = position;
        this.vitessePixelSec = Params.vitesseRamier;
        this.chargerImage(Params.cheminRamier);
    }

}