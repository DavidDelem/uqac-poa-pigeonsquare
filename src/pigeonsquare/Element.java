package pigeonsquare;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pigeonsquare.utils.Position;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Element extends Parent {

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
            this.imageView = new ImageView(image);

            imageView.setX(this.position.x);
            imageView.setY(this.position.y);
            imageView.setFitHeight(image.getHeight());
            imageView.setPreserveRatio(true);

            System.out.println("TEST"); //TODO : afficher 2 fois ! L'image ne s'affiche pas
            this.getChildren().add(imageView);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}