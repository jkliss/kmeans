package sample;

/**
 * Created by julius on 02.09.16.
 */
public class Dist {
    double dist = 0;

    public Dist(Point a, Point b){
        double xa = a.getX();
        double ya = a.getY();
        double xb = b.getX();
        double yb = b.getY();
        dist = Math.sqrt(Math.pow(xa-xb,2)+Math.pow(ya-yb,2));
    }

    public double getDist() {
        return dist;
    }
}
