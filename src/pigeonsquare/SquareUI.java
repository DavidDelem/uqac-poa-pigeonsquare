package pigeonsquare;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pigeonsquare.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class SquareUI extends Application {

    private final int width = 950;
    private final int height = 650;

    private List<Element> elementList;

    public SquareUI() {

    }

    @Override
    public void start(Stage primaryStage) {

        this.elementList = new ArrayList<>();

        StackPane root = new StackPane();
        root.setId("pane");
        Scene scene = new Scene(root, width, height);

        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        scene.setOnMouseClicked(me -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                System.out.println("PRIMARY");
                Element element = Square.getInstance().ajouterPigeonAleatoire(new Position((int)me.getSceneX(), (int)me.getSceneY()));
                this.elementList.add(element);

            } else if  (me.getButton() == MouseButton.SECONDARY) {
                System.out.println("SECONDARY");
            } else if  (me.getButton() == MouseButton.MIDDLE) {
                System.out.println("MIDDLE");
            }

            System.out.println("X: " + (me.getSceneX()));
            System.out.println("Y: " + (me.getSceneY()));

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
