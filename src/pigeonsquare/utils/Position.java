package pigeonsquare.utils;

public class Position {

    public double x;
    public double y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distanceEntre(Position p1, Position p2){
        return Math.sqrt(Math.pow(p2.x-p1.x,2)+Math.pow(p2.y-p1.y,2));
    }

    public double getDistance(){
        return Math.sqrt(Math.pow(this.x,2) + Math.pow(this.y,2));
    }

    public void normalisation(){
        double distance = getDistance();
        if(distance!=0){
            this.x = this.x/distance;
            this.y = this.y/distance;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

}
