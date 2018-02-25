package pigeonsquare;


import javafx.application.Platform;
import pigeonsquare.utils.Position;

public abstract class ElementMobile extends Element implements Runnable {

    protected double vitessePixelSec;
    protected int sleepMilli;
    protected Element elementObjectif;
    protected Position directionObjectif;
    protected Position positionDepart;
    protected double distanceObjectif;
    protected boolean arreterThread;
    private boolean sedeplacer = false;

    public ElementMobile() {
        this.vitessePixelSec = 250;
        this.sleepMilli = 33;
        this.elementObjectif = null;
        this.directionObjectif = new Position();
        this.positionDepart = new Position();
        this.distanceObjectif = 0.0f;
        this.arreterThread = false;
    }

    public abstract void determinerObjectif();
    public abstract void surObjectif();

    public Element getElementObjectif(){
        return this.elementObjectif;
    }

    public void seDeplacer(){

        this.position.x += this.directionObjectif.x * (vitessePixelSec/1000.0) * sleepMilli;
        this.position.y += this.directionObjectif.y * (vitessePixelSec/1000.0) * sleepMilli;

        Platform.runLater(() -> {
            imageView.setX(this.position.x-this.decalageLargeur);
            imageView.setY(this.position.y-this.decalageHauteur);
        });

        if (Position.distanceEntre(this.positionDepart, this.position) >= this.distanceObjectif) {
            surObjectif();
        }
    }

    public void stop(){
        this.elementObjectif = null;
    }

    @Override
    public void run() {

        while (!this.arreterThread){

            determinerObjectif();
            if(this.elementObjectif != null) seDeplacer();

            try {
                Thread.sleep(this.sleepMilli);
            } catch (InterruptedException e) {
                System.out.println("Thread ElementMobile interrompu !");
                break;
            }
        }
    }

    public void arreterThread(){
        this.arreterThread = true;
    }

}