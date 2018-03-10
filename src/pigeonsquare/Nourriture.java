package pigeonsquare;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pigeonsquare.utils.Position;
import sun.net.www.content.text.PlainTextInputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Nourriture extends Element {

    private boolean frais;
    private boolean manger;

    public Nourriture(Position position) {
        this.position = position;
        this.frais = true;
        this.manger = false;
        this.chargerImage("ressources/nourriture.png");
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
                    chargerImage("ressources/nourriture-pourrie.png");
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
    }
}