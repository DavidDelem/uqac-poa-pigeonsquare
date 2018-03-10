package pigeonsquare.pigeons;

import pigeonsquare.Params;
import pigeonsquare.utils.Position;

/**
 * Classe Colombin
 *
 */
public class Colombin extends Pigeon {

    public Colombin(Position position) {
        this.position = position;
        this.vitessePixelSec = Params.vitesseColombin;
        this.chargerImage(Params.cheminColombin);
    }

}
