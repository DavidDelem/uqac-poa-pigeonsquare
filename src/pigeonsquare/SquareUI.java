package pigeonsquare;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pigeonsquare.pigeons.Pigeon;
import pigeonsquare.utils.Position;
import java.util.ArrayList;
import java.util.List;

public class SquareUI extends Application {

    private final int width = 950;
    private final int height = 650;

    private static Pane root = new Pane();

    public SquareUI() {

    }

    @Override
    public void start(Stage primaryStage) {

        root.setId("pane");
        root.setStyle("-fx-background-color: #c8e1dd;");
        Scene scene = new Scene(root, width, height);

        scene.setOnMouseClicked(event -> {
            Position pos = new Position((int)event.getSceneX(), (int)event.getSceneY());
            Element element = null;

            if (event.getButton() == MouseButton.PRIMARY) {

                if(Square.getInstance().getNbPigeon() < Square.nbPigeonMax){
                    element = Square.getInstance().ajouterPigeonAleatoire(pos);
                }

            } else if  (event.getButton() == MouseButton.SECONDARY) {
                if(Square.getInstance().getNbNourriture() < Square.nbNourritureMax) {
                    element = Square.getInstance().ajouterNourriture(pos);
                }

            } else if  (event.getButton() == MouseButton.MIDDLE) {
                if(Square.getInstance().getNbCaillou() < Square.nbCaillouMax) {
                    element = Square.getInstance().ajouterCaillou(pos);
                }
            }

            if(element != null){
                SquareUI.ajouterElementGraphique(element.getImageView());
                Thread thread = new Thread(element);
                thread.start();
            }

        });

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event ->{
            if(event.getCode() == KeyCode.R) Square.getInstance().reinitialiser();
        });

        primaryStage.setTitle("PigeonSquare");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void supprimerElementGraphique(Node node){
        root.getChildren().remove(node);
    }

    public static void ajouterElementGraphique(Node node){
        root.getChildren().add(node);
    }

    public static void main(String args[])
    {
        launch(args);
    }

}
