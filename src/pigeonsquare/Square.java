package pigeonsquare;

import javafx.application.Platform;
import pigeonsquare.pigeons.Biset;
import pigeonsquare.pigeons.Colombin;
import pigeonsquare.pigeons.Pigeon;
import pigeonsquare.pigeons.Ramier;
import pigeonsquare.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Square {

    // TODO : locker les variable
    // TODO : faire une fonction reset
    private List<Pigeon> pigeonList;
    private List<Nourriture> nourritureList;
    private List<Caillou> caillouList;

    public static final int nbPigeonMax = 10;
    public static final int nbNourritureMax = 3;
    public static final int nbCaillouMax = 2;

    private Square() {
        this.pigeonList = new ArrayList<>();
        this.nourritureList = new ArrayList<>();
        this.caillouList = new ArrayList<>();
    }

    /* Singleton */

    private static Square square = new Square();
    public static Square getInstance()
    {
        return square;
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

    public Element ajouterCaillou(Position position) {
        Element element;
        element = new Caillou(position);
        caillouList.add((Caillou) element);
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
            double distanceTmp = Position.distanceEntre(pigeon.getPosition(), nourriture.getPosition());
            if(distance == 0.0f || distanceTmp < distance) {
                distance = distanceTmp;
                nourriturePlusProche = nourriture;
            }
        }

        return nourriturePlusProche;
    }

    public void supprimerNourriture(Nourriture nourriture){

        for(Pigeon pigeon : this.pigeonList){
            if(pigeon.getElementObjectif() == nourriture) pigeon.stop();
        }

        this.nourritureList.remove(nourriture);
        Platform.runLater(()->{
            SquareUI.supprimerElementGraphique(nourriture.getImageView());
        });

    }

    public void supprimerCaillou(Caillou caillou){
        this.caillouList.remove(caillou);
        Platform.runLater(()->{
            SquareUI.supprimerElementGraphique(caillou.getImageView());
        });
    }

    public Caillou caillouProche(Pigeon pigeon, int distanceDangerMin){

        Caillou caillouPlusProche = null;
        double distance = 0.0f;

        for(Caillou caillou : this.caillouList){
            double distanceTmp = Position.distanceEntre(pigeon.getPosition(), caillou.getPosition());
            if(distanceTmp <= distanceDangerMin && (distanceTmp < distance || distance == 0.0f)) {
                distance = distanceTmp;
                caillouPlusProche = caillou;
            }
        }
        return caillouPlusProche;
    }


    public int getNbPigeon(){
        return this.pigeonList.size();
    }

    public int getNbNourriture(){
        return this.nourritureList.size();
    }

    public int getNbCaillou(){
        return this.caillouList.size();
    }

    public void reinitialiser(){

        List<Element> elementList = new ArrayList<>();
        elementList.addAll(this.pigeonList);
        elementList.addAll(this.caillouList);
        elementList.addAll(this.nourritureList);

        for(Element element : elementList){
            SquareUI.supprimerElementGraphique(element.getImageView());
            element.arreterThread();
        }

        this.pigeonList.clear();
        this.caillouList.clear();
        this.nourritureList.clear();
    }
}
