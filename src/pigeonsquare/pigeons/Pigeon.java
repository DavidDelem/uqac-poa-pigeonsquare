package pigeonsquare.pigeons;

import pigeonsquare.*;
import pigeonsquare.utils.Position;

import java.util.List;

public class Pigeon extends ElementMobile {

    public Pigeon() {
    }

    public void determinerObjectif() {

        Nourriture nourriture = Square.getInstance().nourriturePlusProche(this);
        //List<Chien> chiensList = Square.getInstance().chiensPlusProches(this, distangeDanger);

        if(nourriture != null && nourriture != this.elementObjectif){

            this.elementObjectif = nourriture;
            this.positionDepart = new Position(this.position.x, this.position.y);
            this.distanceObjectif = Position.distanceEntre(this.position, nourriture.getPosition());

            Position direction = new Position(
                    nourriture.getPosition().x-this.position.x,
                    nourriture.getPosition().y-this.position.y
            );
            direction.normalisation();
            this.directionObjectif = direction;

        }
    }

    public void surObjectif(){
        Square.getInstance().supprimerNourriture((Nourriture)elementObjectif);
        stop();
    }

}
