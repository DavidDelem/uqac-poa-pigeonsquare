package pigeonsquare;

import pigeonsquare.pigeons.Biset;
import pigeonsquare.pigeons.Colombin;
import pigeonsquare.pigeons.Pigeon;
import pigeonsquare.pigeons.Ramier;
import pigeonsquare.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Square {

    private List<Pigeon> pigeonList;
    private List<Nourriture> nourritureList;
    private List<Chien> chienList;

    private Square() {
        this.pigeonList = new ArrayList<>();
        this.nourritureList = new ArrayList<>();
        this.chienList = new ArrayList<>();
        boucleJeu();
    }

    /* Singleton */

    private static Square square = new Square();
    public static Square getInstance()
    {
        return square;
    }

    public void boucleJeu(){
    }

    public Element ajouterPigeonAleatoire(Position position) {
        Random random = new Random();
        Element element;

        switch (random.nextInt(4-1) + 1) {
            case 1:
                element = new Biset(position);
                pigeonList.add((Pigeon)element);
                return element;
            case 2:
                element = new Colombin(position);
                pigeonList.add((Pigeon)element);
                return element;
            case 3:
                element = new Ramier(position);
                pigeonList.add((Pigeon)element);
                return element;
            default:
                return  null;
        }
    }

    public Element ajouterChien(Position position) {
        Element element;
        element = new Chien(position);
        chienList.add((Chien)element);
        return element;
    }

    public Element ajouterNourriture(Position position) {
        Element element;
        element = new Nourriture(position);
        nourritureList.add((Nourriture)element);
        return element;
    }

    public Nourriture nourriturePlusProche(Pigeon pigeon){

        Nourriture nourriturePlusProche = null;
        double distance = 0.0f;

        for(Nourriture nourriture : this.nourritureList){
            double distanceTmp = Position.calculerDistance(pigeon.getPosition(), nourriture.getPosition());
            if(distance == 0.0f || distanceTmp < distance) {
                distance = distanceTmp;
                nourriturePlusProche = nourriture;
            }
        }

        return nourriturePlusProche;
    }
}
