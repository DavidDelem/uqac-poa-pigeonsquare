package pigeonsquare;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pigeonsquare.utils.Position;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Element implements Runnable {

    protected Position position;

    protected ImageView imageView;
    protected double decalageLargeur;
    protected double decalageHauteur;
    protected boolean arreterThread;

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

    public void arreterThread(){
        this.arreterThread = true;
    }


}