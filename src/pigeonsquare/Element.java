package pigeonsquare;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pigeonsquare.utils.Position;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Classe abstraite Element
 *
 */
public abstract class Element implements Runnable {

    protected Position position;

    protected ImageView imageView;
    protected double decalageLargeur;
    protected double decalageHauteur;
    protected boolean arreterThread;

    /**
     * Initialise un élément
     *
     */
    public Element(){
        this.arreterThread = false;
    }

    public abstract void run();

    public Position getPosition() {
        return position;
    }
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Charger une image pour un élément
     *
     * @param chemin d'accès de l'image à charger
     */
    public void chargerImage(String chemin){
        try {
            Image image = new Image(new FileInputStream(chemin));
            this.imageView = new ImageView();
            this.imageView.setImage(image);

            this.decalageLargeur = image.getWidth()/2.0;
            this.decalageHauteur = image.getHeight()/2.0;
            imageView.setX(this.position.x-this.decalageLargeur);
            imageView.setY(this.position.y-this.decalageHauteur);
            imageView.setFitHeight(image.getHeight());
            imageView.setPreserveRatio(true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Activer la condition d'arrêt du thread de l'élément
     *
     */
    public void arreterThread(){
        this.arreterThread = true;
    }


}