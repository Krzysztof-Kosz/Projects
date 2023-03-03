package dev.iad2022.issgroup.isstracker.mapping;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.Light;

public class EarthPoint {
    double longitude;
    double latitude;

    public EarthPoint(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Point2D convertTo2D(Canvas canvas, EarthView earthView) {
        double EarthWidth = canvas.getWidth();
        double EarthHeight = canvas.getHeight();

        var y = (1 - (this.latitude - earthView.minLat) / (earthView.maxLat - earthView.minLat)) * EarthHeight;
        var x = (this.longitude  - earthView.minLon) * EarthWidth / (earthView.maxLon - earthView.minLon);

        return new Point2D(x,y);
    }

}
