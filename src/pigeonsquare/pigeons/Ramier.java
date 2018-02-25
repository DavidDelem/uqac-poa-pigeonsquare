package pigeonsquare.pigeons;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pigeonsquare.utils.Position;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ramier extends Pigeon {

    public Ramier(Position position) {
        this.position = position;
        this.vitessePixelSec = 500;
        this.chargerImage("ressources/ramier.png");
    }

}