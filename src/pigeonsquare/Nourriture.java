package pigeonsquare;

import javafx.application.Platform;
import pigeonsquare.utils.Position;
import java.util.concurrent.locks.ReentrantLock;

public class Nourriture extends Element {

    private boolean frais;
    private boolean manger;
    private ReentrantLock lock;

    public Nourriture(Position position) {
        this.position = position;
        this.frais = true;
        this.manger = false;
        this.chargerImage("ressources/nourriture.png");
        this.lock = new ReentrantLock();
    }

    public boolean getFrais(){
        return this.frais;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(4000);

            this.frais = false;

            if(!this.arreterThread && !this.manger){
                Platform.runLater(() -> {
                    SquareUI.supprimerElementGraphique(this.imageView);
                    chargerImage("ressources/nourriture2.png"); // TODO : changer texture nourriture pas fraiche
                    SquareUI.ajouterElementGraphique(this.imageView);
                });

                Thread.sleep(4000);
                Square.getInstance().supprimerNourriture(this);
            }

        } catch (InterruptedException e) {
            System.out.println("Thread Caillou interrompu !");
        }
    }

    public void manger(){

        this.manger = true;
        if(this.lock.tryLock()) Square.getInstance().supprimerNourriture(this);
    }


}