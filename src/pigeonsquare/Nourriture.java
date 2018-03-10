package pigeonsquare;

import javafx.application.Platform;
import pigeonsquare.utils.Position;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe Nourriture
 *
 */
public class Nourriture extends Element {

    private boolean frais;
    private boolean manger;
    private ReentrantLock lock;

    /**
     * Initialise une nourriture
     *
     * @param position de la nourriture
     */
    public Nourriture(Position position) {
        this.position = position;
        this.frais = true;
        this.manger = false;
        this.chargerImage(Params.cheminNourriture);
        this.lock = new ReentrantLock();
    }

    public boolean getFrais(){
        return this.frais;
    }

    /**
     * Méthode d'exécution du thread nourriture
     *
     */
    @Override
    public void run() {

        try {
            //Nourriture fraîche pendant un certain temps
            Thread.sleep(Params.nourritureSleep);
            this.frais = false;

            //Si on n'a pas arrêté le thread et que la nourriture n'a pas été mangée
            if(!this.arreterThread && !this.manger){
                Platform.runLater(() -> {
                    SquareUI.supprimerElementGraphique(this.imageView);
                    chargerImage(Params.cheminNourriturePourrie);
                    SquareUI.ajouterElementGraphique(this.imageView);
                });

                Thread.sleep(Params.nourritureSleep);
                Square.getInstance().supprimerNourriture(this);
            }

        } catch (InterruptedException e) {
            System.out.println("Thread Caillou interrompu !");
        }
    }

    /**
     * Lorsqu'une nourriture est mangée
     *
     */
    public void manger(){

        this.manger = true;
        if(this.lock.tryLock()) Square.getInstance().supprimerNourriture(this);
    }


}