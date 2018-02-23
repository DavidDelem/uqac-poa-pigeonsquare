package pigeonsquare.pigeons;

import pigeonsquare.Chien;
import pigeonsquare.ElementMobile;
import pigeonsquare.Nourriture;
import pigeonsquare.Square;
import pigeonsquare.utils.Position;

import java.util.List;

public class Pigeon extends ElementMobile {

    private int distangeDanger;

    public Pigeon() {
        this.distangeDanger = 300;
    }

    public Position determinerProchainePosition() {

        Nourriture nourriture = Square.getInstance().nourriturePlusProche(this);
        //List<Chien> chiensList = Square.getInstance().chiensPlusProches(this, distangeDanger);

        if(nourriture != null) return nourriture.getPosition();
        else return this.position;
    }

}
