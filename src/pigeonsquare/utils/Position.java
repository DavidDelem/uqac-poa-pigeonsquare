package pigeonsquare.utils;

import pigeonsquare.Params;

/**
 * Classe Position
 *
 */
public class Position {

    public double x;
    public double y;

    /**
     * Initialise une position
     *
     */
    public Position() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Initialise une position avec des coordonnées définies
     *
     * @param x position en x
     * @param y position en y
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculer la distance entre 2 positions
     *
     * @param p1 position 1
     * @param p2 position 2
     * @return la distance dans un double
     */
    public static double distanceEntre(Position p1, Position p2){
        return Math.sqrt(Math.pow(p2.x-p1.x,2)+Math.pow(p2.y-p1.y,2));
    }

    /**
     * Récupérer la distance entre le point d'origine 0,0 (coin supérieur gauche) et la position de l'instance
     *
     * @return la distance décrite ci-dessus
     */
    public double getDistance(){
        return Math.sqrt(Math.pow(this.x,2) + Math.pow(this.y,2));
    }

    /**
     * Méthode qui normalise un vecteur
     *
     */
    public void normalisation(){
        double distance = getDistance();
        if(distance!=0){
            this.x = this.x/distance;
            this.y = this.y/distance;
        }
    }

    /**
     * Vérifier si l'instance est éguale à une autre Position
     *
     * @param o autre position à comparer avec celle de l'instance
     * @return true si elles sont égales, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    /**
     * Vérifier si l'instance est proche d'une autre Position
     *
     * @param position autre position à comparer avec celle de l'instance
     * @return true si elles sont proches, sinon false
     */
    public boolean proche(Position position){
        return Math.abs(this.x - position.x) < Params.positionProche && Math.abs(this.y - position.y) < Params.positionProche;
    }

}
