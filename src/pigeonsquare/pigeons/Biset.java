package pigeonsquare.pigeons;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pigeonsquare.utils.Position;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Biset extends Pigeon {

    public Biset(Position position) {
        this.position = position;
        this.chargerImage("ressources/biset.png");
    }

}
