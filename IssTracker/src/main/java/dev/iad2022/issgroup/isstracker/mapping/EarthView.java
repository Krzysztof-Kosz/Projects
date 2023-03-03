package dev.iad2022.issgroup.isstracker.mapping;

import dev.iad2022.issgroup.isstracker.Application;
import dev.iad2022.issgroup.isstracker.views.ViewType;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.List;
import java.util.Objects;

public class EarthView implements Mappable{
    private static final String BASE_MAP = "map_day.jpg";
    private static final String DEFAULT_MAP = "default_map.jpg";

    private static final String POLAND_MAP = "poland_map.jpg";
    private static final String ISS_IMAGE = "iss.png";
    private static final int RADIUS = 3;

    double maxLat;
    double minLat;

    double maxLon;
    double minLon;

    ViewType viewType;


    public void setView(ViewType viewType) {
        this.viewType = viewType;
        if(viewType == ViewType.POLAND) {
            maxLat = 56.0000;
            minLat = 48.0000;
            maxLon = 25.0000;
            minLon = 13.0000;
        }
        if(viewType == ViewType.MAP) {
            maxLat = 90;
            minLat = -90;
            maxLon = 180;
            minLon = -180;
        }
    }

    @Override
    public Image getImage() {
        String mapSource;
        if(viewType == ViewType.POLAND){
            mapSource = POLAND_MAP;
        }
        else {
            mapSource = DEFAULT_MAP;
        }

        return new Image(Objects.requireNonNull(Application.class.getResource(mapSource)).toExternalForm());
    }

    @Override
    public void drawPoint(Canvas canvas, Point2D point2D) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.YELLOW);

        gc.strokeOval(point2D.getX() - RADIUS, point2D.getY() - RADIUS, RADIUS * 2, RADIUS * 2);
        gc.fillOval(point2D.getX() - RADIUS, point2D.getY() - RADIUS, RADIUS * 2, RADIUS * 2);
    }



    public void drawLineFromLatLon(Canvas canvas, EarthPoint[] earthPoints) {
        int n = earthPoints.length;
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(Color.CORNFLOWERBLUE);

        Point2D[] points2D = new Point2D[n];

        for(int i = 0; i < n; i++){
            points2D[i] = earthPoints[i].convertTo2D(canvas, this);

            if(i > 0){
                if(points2D[i - 1].getX() > points2D[i].getX()) {
                    gc.strokePolyline(new double[]{points2D[i - 1].getX() , points2D[i].getX() + canvas.getWidth()},
                            new double[]{points2D[i - 1].getY(), points2D[i].getY()}, 2);
                    gc.strokePolyline(new double[]{points2D[i - 1].getX() - canvas.getWidth(), points2D[i].getX()},
                            new double[]{points2D[i - 1].getY(), points2D[i].getY()}, 2);
                }
                else {
                    gc.strokePolyline(new double[]{points2D[i - 1].getX(), points2D[i].getX()},
                            new double[]{points2D[i - 1].getY(), points2D[i].getY()}, 2);
                }

            }
            //drawPoint(canvas, point2D[i]);

        }


    }

    public void drawISS(Canvas canvas, Point2D point2D){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int imageWidth = 50;
        int imageHeight = 50;

        Image image = new Image(Objects.requireNonNull(Application.class.getResource(ISS_IMAGE)).toExternalForm(),
                imageWidth, imageHeight, true, false);

        // in this case I think we need to center this image manually
        double xCord = point2D.getX() - (imageWidth * 0.5) + 10;
        double yCord = point2D.getY() - (imageHeight * 0.5);
        gc.drawImage(image, xCord, yCord);
        //but this ought to be a temporary solution only
    }

    @Override
    public void drawPointFromLatLon(Canvas canvas, EarthPoint earthPoint) {
        Point2D point2D = earthPoint.convertTo2D(canvas, this);
        drawISS(canvas, point2D);
        drawPoint(canvas, point2D);
        //drawPoint(canvas, point2D);
    }


}
