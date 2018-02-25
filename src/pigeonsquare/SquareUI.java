package pigeonsquare;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
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

    private List<Element> elementList;
    private static Pane root = new Pane();

    public SquareUI() {

    }

    @Override
    public void start(Stage primaryStage) {

        this.elementList = new ArrayList<>();

        root.setId("pane");
        Scene scene = new Scene(root, width, height);

        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        scene.setOnMouseClicked(me -> {
            Position pos = new Position((int)me.getSceneX(), (int)me.getSceneY());
            Element element = null;

            if (me.getButton() == MouseButton.PRIMARY) {

                if(Square.getInstance().getNbPigeon() < Square.nbPigeonMax){
                    element = Square.getInstance().ajouterPigeonAleatoire(pos);
                    this.elementList.add(element);
                    Thread thread = new Thread((Pigeon)element);
                    thread.start();
                }

            } else if  (me.getButton() == MouseButton.SECONDARY) {
                if(Square.getInstance().getNbNourriture() < Square.nbNourritureMax) {
                    element = Square.getInstance().ajouterNourriture(pos);
                    this.elementList.add(element);
                }

            } else if  (me.getButton() == MouseButton.MIDDLE) {
                if(Square.getInstance().getNbCaillou() < Square.nbCaillouMax) {
                    element = Square.getInstance().ajouterCaillou(pos);
                    this.elementList.add(element);
                }
            }

            if(element != null) root.getChildren().add(element.getImageView());

        });

        primaryStage.setTitle("PigeonSquare");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void supprimerElementGraphique(Node node){
        root.getChildren().remove(node);
    }

    public static void main(String args[])
    {
        launch(args);
    }

}
