package pigeonsquare;

import javafx.geometry.Pos;
import pigeonsquare.pigeons.Biset;
import pigeonsquare.pigeons.Colombin;
import pigeonsquare.pigeons.Pigeon;
import pigeonsquare.pigeons.Ramier;
import pigeonsquare.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Square {

    private List<Pigeon> pigeonList;
    private List<Nourriture> nourritureList;
    private List<Chien> chienList;

    private Square() {
        this.pigeonList = new ArrayList<>();
        this.nourritureList = new ArrayList<>();
        this.chienList = new ArrayList<>();
    }

    /* Singleton */

    private static Square square = new Square();
    static Square getInstance()
    {
        return square;
    }

    public Element ajouterPigeonAleatoire(Position position) {
        Random random = new Random();
        Element element;

        switch (random.nextInt(3-1) + 1) {
            case 1:
                element = new Biset(position);
                pigeonList.add(new Biset(position));
                return element;
            case 2:
                element = new Colombin(position);
                pigeonList.add(new Colombin(position));
                return element;
            case 3:
                element = new Ramier(position);
                pigeonList.add(new Ramier(position));
                return element;
            default:
                return  null;
        }
    }
}
