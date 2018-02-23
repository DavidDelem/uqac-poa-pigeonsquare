package pigeonsquare;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import pigeonsquare.utils.Position;

public abstract class ElementMobile extends Element implements Runnable {

    protected double vitesse=250;
    private TranslateTransition translationTransition;

    public ElementMobile() {
        this.translationTransition = new TranslateTransition();
    }

    public abstract Position determinerProchainePosition();

    public void seDeplacer(Position positionCible){

        double distance = Position.calculerDistance(this.position, positionCible);

        this.translationTransition.setDuration(Duration.seconds(distance/vitesse));
        this.translationTransition.setNode(this.imageView);

        this.translationTransition.setByX(positionCible.x-this.position.x);
        this.translationTransition.setByY(positionCible.y-this.position.y);
        this.translationTransition.setCycleCount(1);
        this.translationTransition.setAutoReverse(false);

        Platform.runLater(() -> translationTransition.play());
    }

    public void stop(){

    }

    @Override
    public void run() {

        while (true){
            seDeplacer(determinerProchainePosition());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread pigeon interrompu !");
                break;
            }
        }
    }

}