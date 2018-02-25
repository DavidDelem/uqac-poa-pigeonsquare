package pigeonsquare;

import pigeonsquare.utils.Position;

public class Caillou extends Element {

    public Caillou(Position position) {
        this.position = position;
        this.chargerImage("ressources/pierre.png");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            Square.getInstance().supprimerCaillou(this);

        } catch (InterruptedException e) {
            System.out.println("Thread Caillou interrompu !");
        }
    }

}
