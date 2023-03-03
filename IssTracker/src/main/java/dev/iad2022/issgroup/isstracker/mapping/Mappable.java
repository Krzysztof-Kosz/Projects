package dev.iad2022.issgroup.isstracker.mapping;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public interface Mappable {
    public Image getImage();
    public void drawPoint(Canvas canvas, Point2D point2D);
    public void drawPointFromLatLon(Canvas canvas, EarthPoint earthPoint);
}
