package pigeonsquare.pigeons;

import pigeonsquare.Params;
import pigeonsquare.utils.Position;

/**
 * Classe Biset
 *
 */
public class Biset extends Pigeon {

    public Biset(Position position) {
        this.position = position;
        this.vitessePixelSec = Params.vitesseBiset;
        this.chargerImage(Params.cheminBiset);
    }

}
