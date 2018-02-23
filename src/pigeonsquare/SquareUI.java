package pigeonsquare;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pigeonsquare.utils.Position;
import java.util.ArrayList;
import java.util.List;

public class SquareUI extends Application {

    private final int width = 950;
    private final int height = 650;

    private List<Element> elementList;
    private Pane root;

    public SquareUI() {

    }

    @Override
    public void start(Stage primaryStage) {

        this.elementList = new ArrayList<>();

        this.root = new Pane();
        this.root.setId("pane");
        Scene scene = new Scene(this.root, width, height);

        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        scene.setOnMouseClicked(me -> {
            Position pos = new Position((int)me.getSceneX(), (int)me.getSceneY());
            Element element = null;

            if (me.getButton() == MouseButton.PRIMARY) {
                element = Square.getInstance().ajouterPigeonAleatoire(pos);
                this.elementList.add(element);

            } else if  (me.getButton() == MouseButton.SECONDARY) {
                element = Square.getInstance().ajouterNourriture(pos);
                this.elementList.add(element);

            } else if  (me.getButton() == MouseButton.MIDDLE) {
                element = Square.getInstance().ajouterChien(pos);
                this.elementList.add(element);
            }

            if(element != null){
                this.root.getChildren().add(element.getImageView());
                Thread thread = new Thread(element);
                thread.start();
            }

        });

        primaryStage.setTitle("PigeonSquare");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String args[])
    {
        launch(args);
    }

}
