package pigeonsquare;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pigeonsquare.utils.Position;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Element extends Parent implements Runnable {

    protected Position position;
    protected ImageView imageView;

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

            imageView.setX(this.position.x-image.getWidth()/2);
            imageView.setY(this.position.y-image.getHeight()/2);
            imageView.setFitHeight(image.getHeight());
            imageView.setPreserveRatio(true);

            this.getChildren().add(imageView);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){

        }
    }

}