package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML
    public static ScatterChart diag; // value will be injected by the FXMLLoader

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        Kmeans cluster = calculate();
        primaryStage.setTitle("K-Means");
        final NumberAxis xAxis = new NumberAxis(cluster.getMinX()*0.9, cluster.getMaxX()*1.1, 1);
        final NumberAxis yAxis = new NumberAxis(cluster.getMinY()*0.9, cluster.getMaxY()*1.1, 1);
        final ScatterChart<Number,Number> sc = new
                ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("X variable");
        yAxis.setLabel("Y variable");
        sc.setTitle("Clustering");

        XYChart.Series points = new XYChart.Series();
        points.setName("Data Points");
        points = cluster.getPoints(points);

        XYChart.Series ks = new XYChart.Series();
        ks.setName("K's");
        ks = cluster.getKs(ks);

        sc.getData().addAll(ks, points);
        Scene scene  = new Scene(sc, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setScene(new Scene(root, 500, 500));
        //primaryStage.show();


    }


    public static void main(String[] args) {

        launch(args);
    }

    public Kmeans calculate(){
        double[] pts = {1,1,
                2,2,
                3,2,
                4,5,
                5,5,
                6,5.5,
                4.4,3.3,
                3.2,5,
                1.5,2};
        Kmeans cluster = new Kmeans(pts);
        cluster.returnPoints();
        cluster.setKtimes(3);
        cluster.calcKmeans();
        return cluster;
    }


    public void dia(){
        //stage.setTitle("Scatter Chart Sample");
    }

}
