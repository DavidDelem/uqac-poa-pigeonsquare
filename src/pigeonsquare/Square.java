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

/**
 * Classe Square : contrôleur du Pigeon Square
 *
 */
public class Square {

    private List<Pigeon> pigeonList;
    private List<Nourriture> nourritureList;
    private List<Caillou> caillouList;

    public static final int nbPigeonMax = Params.nbPigeonMax;
    public static final int nbNourritureMax = Params.nbNourritureMax;
    public static final int nbCaillouMax = Params.nbCaillouMax;

    /**
     * Initialise le Square (Singleton !)
     *
     */
    private Square() {
        this.pigeonList = new ArrayList<>();
        this.nourritureList = new ArrayList<>();
        this.caillouList = new ArrayList<>();
    }

    //Singleton
    private static Square square = new Square();
    public static Square getInstance()
    {
        return square;
    }


    /**
     * Ajouter un pigeon aléatoirement parmi 3 races à une position donnée
     *
     * @param position position à laquelle le nouveau pigeon est positionné
     * @return le nouveau pigeon
     */
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

    /**
     * Ajouter un caillou à une position donnée
     *
     * @param position position à laquelle le nouveau caillou est positionné
     * @return le nouveau caillou
     */
    public Element ajouterCaillou(Position position) {
        Element element;
        element = new Caillou(position);
        caillouList.add((Caillou) element);
        return element;
    }

    /**
     * Ajouter une nourriture à une position donnée
     *
     * @param position position à laquelle la nourriture est positionnée
     * @return la nouvelle nourriture
     */
    public Element ajouterNourriture(Position position) {
        Element element;
        element = new Nourriture(position);
        nourritureList.add((Nourriture)element);
        return element;
    }

    /**
     * Pour un pigeon donné, permet de connaître la nourriture la plus proche
     *
     * @param pigeon pour lequel il faut faire la recherche
     * @return la nourriture la plus proche (à condition qu'il en existe)
     */
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

    /**
     * Supprimer une nourriture
     *
     * @param nourriture a supprimer
     */
    public synchronized void supprimerNourriture(Nourriture nourriture){

        for(Pigeon pigeon : this.pigeonList){
            if(pigeon.getElementObjectif() == nourriture) pigeon.stop();
        }

        this.nourritureList.remove(nourriture);
        Platform.runLater(()->{
            SquareUI.supprimerElementGraphique(nourriture.getImageView());
        });

    }

    /**
     * Supprimer un caillou
     *
     * @param caillou a supprimer
     */
    public synchronized void supprimerCaillou(Caillou caillou){
        this.caillouList.remove(caillou);
        Platform.runLater(()->{
            SquareUI.supprimerElementGraphique(caillou.getImageView());
        });
    }

    /**
     * Pour un pigeon donné et une zone de danger donnée, permet de connaître le caillou le plus proche
     *
     * @param pigeon pour lequel il faut faire la recherche
     * @param distanceDangerMin distance pour laquelle un caillou est compris dans la zone de danger
     * @return le caillou le plus proche dans la zone de donnée
     */
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

    /**
     * Reinitialiser l'environnement
     *
     */
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
