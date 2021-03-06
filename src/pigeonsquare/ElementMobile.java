package pigeonsquare;


import javafx.application.Platform;
import pigeonsquare.utils.Position;

/**
 * Classe ElementMobile
 *
 */
public abstract class ElementMobile extends Element {

    protected double vitessePixelSec;
    protected int sleepMilli;
    protected Element elementObjectif;
    protected Position direction;
    private boolean sedeplacer;

    /**
     * Initialise un élément mobile
     *
     */
    public ElementMobile() {
        this.vitessePixelSec = Params.vitesseParDefaut;
        this.sleepMilli = Params.sleepMilli;
        this.elementObjectif = null;
        this.direction = new Position();
        this.sedeplacer = false;
    }

    public abstract Position calculerDirection();
    public abstract void surObjectif();

    public Element getElementObjectif(){
        return this.elementObjectif;
    }

    /**
     * Déplacement d'un élément mobile
     *
     */
    public void seDeplacer(){

        this.position.x += this.direction.x * (vitessePixelSec/1000.0) * sleepMilli;
        this.position.y += this.direction.y * (vitessePixelSec/1000.0) * sleepMilli;

        Platform.runLater(() -> {
            imageView.setX(this.position.x-this.decalageLargeur);
            imageView.setY(this.position.y-this.decalageHauteur);
        });

        if (this.elementObjectif != null && this.position.proche(this.elementObjectif.getPosition())) {
            surObjectif();
        }
    }

    /**
     * Arrêter le déplacement d'un élément mobile
     *
     */
    public void stop(){
        this.elementObjectif = null;
    }

    /**
     * Méthode d'exécution du thread d'un élément mobile
     *
     */
    @Override
    public void run() {

        while (!this.arreterThread){

            this.direction = calculerDirection();
            if(this.direction != null) seDeplacer();

            try {
                Thread.sleep(this.sleepMilli);
            } catch (InterruptedException e) {
                System.out.println("Thread ElementMobile interrompu !");
                break;
            }
        }
    }

}