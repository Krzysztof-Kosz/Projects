package dev.iad2022.issgroup.isstracker.APIrequest;
import dev.iad2022.issgroup.isstracker.mapping.EarthPoint;

public class ISSData {
    public final double latitude;
    public final double longitude;
    public final double altitude;
    public final double speed;

    public ISSData(double latitude, double longitude, double altitude, double speed) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
    }

    public EarthPoint getEarthPoint(){
        return new EarthPoint(this.latitude, this.longitude);
    }


}
