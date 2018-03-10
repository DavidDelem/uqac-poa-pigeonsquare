package pigeonsquare;

import pigeonsquare.utils.Position;

/**
 * Classe Caillou
 *
 */
public class Caillou extends Element {

    /**
     * Initialise un caillou
     *
     * @param position à laquelle le caillou est positionné
     */
    public Caillou(Position position) {
        this.position = position;
        this.chargerImage(Params.cheminCaillou);
    }

    /**
     * Méthode d'exécution du thread d'un caillou
     *
     */
    @Override
    public void run() {
        try {
            Thread.sleep(Params.caillouSleep);
            Square.getInstance().supprimerCaillou(this);

        } catch (InterruptedException e) {
            System.out.println("Thread Caillou interrompu !");
        }
    }

}
