package pigeonsquare.pigeons;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pigeonsquare.utils.Position;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Colombin extends Pigeon {

    public Colombin(Position position) {
        this.position = position;
        this.vitesse = 350;
        this.chargerImage("ressources/colombin.png");
    }

}
