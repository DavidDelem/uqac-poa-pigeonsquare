package pigeonsquare.pigeons;

import pigeonsquare.Chien;
import pigeonsquare.ElementMobile;
import pigeonsquare.Nourriture;
import pigeonsquare.Square;
import pigeonsquare.utils.Position;

import java.util.List;

public class Pigeon extends ElementMobile {

    private int distangeDanger;
    private Nourriture nourriture;

    public Pigeon() {
        this.distangeDanger = 300;
        nourriture = null;
    }

    public Position determinerProchainePosition() {

        Nourriture nourriture = Square.getInstance().nourriturePlusProche(this);
        //List<Chien> chiensList = Square.getInstance().chiensPlusProches(this, distangeDanger);

//        System.out.println("this.position.x = "+this.position.x+ "  ; this.position.y = " + this.position.y);
//        if(nourriture != null) System.out.println("nourriture.getPosition().x = "+nourriture.getPosition().x+ "  ; nourriture.getPosition().y = " + nourriture.getPosition().y);

        if(nourriture != null) return nourriture.getPosition();
        else return this.position;
    }

    @Override
    public boolean surObjectif(){
        if(nourriture != null && this.position.averageEquals(this.nourriture.getPosition())) return true;
        return false;
    }

}
