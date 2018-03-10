package pigeonsquare;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pigeonsquare.utils.Position;
import java.util.Random;

/**
 * Classe SquareUI : fenêtre graphique
 *
 */
public class SquareUI extends Application {

    private final int width = Params.width;
    private final int height = Params.height;

    private static Pane root = new Pane();


    /**
     * Démarre l'application JavaFX
     *
     * @param primaryStage conteneur de la scène
     */
    @Override
    public void start(Stage primaryStage) {

        root.setId("pane");
        Scene scene = new Scene(root, width, height);

        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        scene.setOnMouseClicked(event -> {
            Position pos = new Position((int)event.getSceneX(), (int)event.getSceneY());
            Element element = null;

            //Ajout d'un nouveau pigeon (aléatoire parmi les 3 races de pigeon) à la position de la souris
            if (event.getButton() == MouseButton.PRIMARY) {

                if(Square.getInstance().getNbPigeon() < Square.nbPigeonMax){
                    element = Square.getInstance().ajouterPigeonAleatoire(pos);
                }
            //Ajout d'une nouvelle nourriture à la position de la souris
            } else if  (event.getButton() == MouseButton.SECONDARY) {
                if(Square.getInstance().getNbNourriture() < Square.nbNourritureMax) {
                    element = Square.getInstance().ajouterNourriture(pos);
                }

            }

            if(element != null){
                SquareUI.ajouterElementGraphique(element.getImageView());
                Thread thread = new Thread(element);
                thread.start();
            }

        });

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event ->{
            //Réinitialiser le jeu
            if(event.getCode() == KeyCode.R) Square.getInstance().reinitialiser();

            //Lancer un caillou à une position aléatoire lorsque la barre espace est utilisée
            if(event.getCode() == KeyCode.SPACE && Square.getInstance().getNbCaillou() < Square.nbCaillouMax){
                Random r = new Random();
                Position pos = new Position(r.nextInt(this.width),  r.nextInt(this.height));
                Element element = Square.getInstance().ajouterCaillou(pos);

                SquareUI.ajouterElementGraphique(element.getImageView());
                Thread thread = new Thread(element);
                thread.start();
            }
        });

        primaryStage.setTitle("PigeonSquare");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Supprimer un élément graphique de la fenêtre
     *
     * @param node noeud graphique à supprimer dans l'objet Pane "root"
     */
    public static void supprimerElementGraphique(Node node){
        root.getChildren().remove(node);
    }

    /**
     * Ajouter un élément graphique à la fenêtre
     *
     * @param node noeud graphique à ajouter dans l'objet Pane "root"
     */
    public static void ajouterElementGraphique(Node node){
        root.getChildren().add(node);
    }

    /**
     * Fonction de lancement du programme
     *
     * @param args paramètres passés au programme
     */
    public static void main(String args[])
    {
        launch(args);
    }

}
