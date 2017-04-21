package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julius on 02.09.16.
 */
public class Cluster {
    Point k;
    List<Point> points = new ArrayList<>();


    public Cluster(Point k){
        this.k = k;
    }

    public void setK(Point k) {
        this.k = k;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public Point clusterMean(){
        double x = 0;
        double y = 0;
        int c = 0;
        for(Point point : points){
            x += point.getX();
            y += point.getY();
            c++;
        }
        if(c > 0) {
            x = x / c;
            y = y / c;
        }
        else {
            x = k.getX();
            y = k.getY();
        }
        Point newK = new Point(x,y);
        return newK;
    }
}
