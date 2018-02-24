package pigeonsquare.utils;

public class Position {

    public double x;
    public double y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double calculerDistance(Position p1, Position p2){
        return Math.sqrt(Math.pow(p2.x-p1.x,2)+Math.pow(p2.y-p1.y,2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    public boolean averageEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x >= position.x-10 &&
                x <= position.x+10 &&
                y >= position.y-10 &&
                y <= position.y+10;
    }

}
