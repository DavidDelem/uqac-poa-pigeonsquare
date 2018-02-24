package pigeonsquare;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.util.Duration;
import pigeonsquare.utils.Position;

import javax.swing.plaf.synth.SynthTextAreaUI;

public abstract class ElementMobile extends Element implements Runnable {

    protected double vitesse=250;
    private TranslateTransition translationTransition;

    private boolean sedeplacer = false;

    public ElementMobile() {
        this.translationTransition = new TranslateTransition();
    }

    public abstract Position determinerProchainePosition();
    public abstract boolean surObjectif();

    public void seDeplacer(Position positionCible){

        double distance = Position.calculerDistance(this.position, positionCible);

        /*this.translationTransition.setDuration(Duration.seconds(distance/vitesse));
        this.translationTransition.setNode(this.imageView);

        this.translationTransition.setByX(positionCible.x-this.position.x);
        this.translationTransition.setByY(positionCible.y-this.position.y);
        this.translationTransition.setCycleCount(1);
        this.translationTransition.setAutoReverse(false);

        this.translationTransition.setOnFinished(event -> {

            this.imageView.setX();
            this.imageView.setY();
            this.imageView.setTranslateX(0);
            this.imageView.setTranslateY(0);
            this.position.x = 5;
            this.position.y = 5;
        });

        Platform.runLater(() -> translationTransition.play());*/


        /*Duration duration = Duration.seconds(distance/this.vitesse);

        Timeline timeline = new Timeline();

        KeyFrame end = new KeyFrame(duration,
                new KeyValue(this.imageView.xProperty(), positionCible.x),
                new KeyValue(this.imageView.yProperty(), positionCible.y));

        timeline.getKeyFrames().add(end);

        timeline.setOnFinished(event -> {
            this.imageView.setX(this.imageView.getX() + this.imageView.getTranslateX());
            this.imageView.setY(this.imageView.getY() + this.imageView.getTranslateY());
            this.imageView.setTranslateX(0);
            this.imageView.setTranslateY(0);
        });

        timeline.play();*/

        sedeplacer = true;
    }

    private void stop() {
        this.translationTransition.pause();
        this.imageView.setX(this.imageView.getX());
        this.imageView.setY(this.imageView.getY());
        this.translationTransition.setByX(0);
        this.translationTransition.setByY(0);
        this.translationTransition.play();
    }

    @Override
    public void run() {

        while (true){

            seDeplacer(determinerProchainePosition());
            if(sedeplacer){
                this.position.x += 1;
                this.position.y += 1;
                this.imageView.setX(this.position.x);
                this.imageView.setY(this.position.y);
            }
            if(surObjectif()) {
                System.out.println("STOOOOOOP");
                //stop();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread pigeon interrompu !");
                break;
            }
        }
    }

}