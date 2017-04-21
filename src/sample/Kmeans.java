package sample;

import javafx.scene.chart.XYChart;

/**
 * Created by julius on 02.09.16.
 */
public class Kmeans {
    Point[] points;
    int ktimes = 1;
    Point[] k = new Point[1];
    double minX = 0;
    double maxX = 10;
    double minY = 0;
    double maxY = 10;
    int run = 0;
    Cluster[] clust;


    public Kmeans(double[] input){
        points = new Point[(int) (input.length/2)];

        for(int i = 0; i < input.length; i+=2){
            points[(int) i/2] = new Point(input[i],input[i+1]);
        }
        setPointRange();
        if(maxX == 0) maxX = 0.1;
        if(maxY == 0) maxY = 0.1;
        k[0] = new Point((Math.random()*(maxX-minX))+minX,(Math.random()*(maxY-minY))+minY);
    }

    public void setKtimes(int ktimes) {
        this.ktimes = ktimes;
        k = new Point[ktimes];
        for(int i = 0; i < ktimes; i++){
            k[i] = new Point((Math.random()*(maxX-minX))+minX,(Math.random()*(maxY-minY))+minY);
        }
    }

    public void calcKmeans(){
        System.out.println(run++);
        Cluster[] cluster = new Cluster[ktimes];
        // Set K for Clusters
        for(int i = 0; i < k.length; i++){
            cluster[i] = new Cluster(k[i]);
        }
        double[][] distMat = new double[ktimes][points.length];
        for(int j = 0; j < points.length; j++){
            // INITIALIZE MINIMUM WITH K0
            Dist dist = new Dist(k[0],points[j]);
            int minK = 0;
            double minDist = dist.getDist();
            // All K's
            for(int i = 0; i < k.length; i++){
                // CLUSTER K INITIALIZE
                //if(j == 0){
                //    cluster[i].setK(k[i]);
                //}
                // DISTANCE MATRIX (MAYBE OBSOLETE)
                distMat[i][j] = dist.getDist();
                dist = new Dist(k[i],points[j]);
                // MINIMUM K DISTANCE
                if(dist.getDist() < minDist){
                    minDist = dist.getDist();
                    minK = i;
                }
            }
            // ADD MINIMUM DISTANCE POINT TO RESPECTIVE CLUSTER
            //System.out.println("Points: " + points[j].getX() + " " + points[j].getY());
            //System.out.println("K-" + minK + " Location: " + k[minK].getX() + " " + k[minK].getY() + "Distance: " + minDist);
            cluster[minK].addPoint(points[j]);
        }
        // RETURN DMAT
        //returnDmat(distMat);
        boolean err = false;
        for(int i = 0; i < cluster.length; i++){
            if(k[i].getX() != cluster[i].clusterMean().getX() || k[i].getY() != cluster[i].clusterMean().getY()){
                err = true;
            }
            k[i] = cluster[i].clusterMean();
        }
        if(err == true){
            calcKmeans();
        }
        else{
            for(int i = 0; i < cluster.length; i++){
                System.out.println(i + " - " +  k[i].getX() + "~" + k[i].getY());
            }
            clust = cluster;
        }
    }

    public void returnPoints(){
        for(int i = 0; i < points.length; i++){
            System.out.println(points[i].getX() + " " + points[i].getY());
        }
    }

    public void returnDmat(double[][] distmat){
        for(int i = 0; i < distmat.length; i++){
            for(int j = 0; j < distmat[0].length; j++){
                System.out.print(distmat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setPointRange(){
        minX = points[0].getX();
        maxX = points[0].getX();
        minY = points[0].getY();
        maxY = points[0].getY();
        for(int i = 0; i < points.length; i++){
            if(minX > points[i].getX()){
                minX = points[i].getX();
            }
            if(maxX < points[i].getX()){
                maxX = points[i].getX();
            }
            if(minY > points[i].getY()){
                minY = points[i].getY();
            }
            if(maxY < points[i].getY()){
                maxY = points[i].getY();
            }
        }
    }

    public void returnK(){
        System.out.println(k.length);
    }

    public void returnKtimes(){
        System.out.println(ktimes);
    }

    public Cluster[] returnCluster(){
        return clust;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public XYChart.Series getKs(XYChart.Series ks){
        for(int i = 0; i < k.length; i++){
            ks.getData().add(new XYChart.Data(k[i].getX(), k[i].getY()));
        }
        return ks;
    }

    public XYChart.Series getPoints(XYChart.Series pointsSet){
        for(int i = 0; i < points.length; i++){
            pointsSet.getData().add(new XYChart.Data(points[i].getX(), points[i].getY()));
        }
        return pointsSet;
    }

}
