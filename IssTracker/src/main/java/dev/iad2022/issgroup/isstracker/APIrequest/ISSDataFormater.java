package dev.iad2022.issgroup.isstracker.APIrequest;

import dev.iad2022.issgroup.isstracker.units.Altitude;
import dev.iad2022.issgroup.isstracker.units.Speed;
import dev.iad2022.issgroup.isstracker.units.Units;

public class ISSDataFormater {
    private final Units units;
    private final ISSData issData;

    private final Speed speed;

    private final Altitude altitude;

    private double roundToFormat(double value){
        return (double) Math.round(value * 100) / 100;
    }

    public ISSDataFormater(ISSData issData, Units units) {
        this.issData = issData;
        this.units = units;
        this.speed = new Speed(issData.speed);
        this.altitude = new Altitude(issData.altitude);
    }

    public String getLatitude(){
        String suffix = this.issData.latitude >= 0 ? "North" : "South";
        double rounded = roundToFormat(this.issData.latitude);
        return Math.abs(rounded) + "° " + suffix;
    }

    public String getLongitude(){
        String suffix = this.issData.longitude >= 0 ? "East" : "West";
        double rounded = roundToFormat(this.issData.longitude);
        return Math.abs(rounded) + "° " + suffix;
    }

    public String getSpeedPerHour(){
        if (Units.IMPERIAL.equals(units)) {
            return roundToFormat(speed.inMilesPerHour()) + " miles/h";
        }
        return roundToFormat(speed.inKilometersPerHour()) + " km/h";
    }

    public String getSpeedPerSecond(){
        if (Units.IMPERIAL.equals(units)) {
            return roundToFormat(speed.inFeetPerSecond()) + " feet/s";
        }
        return roundToFormat(speed.inMetersPerSecond()) + " m/s";
    }

    public String getAltitude(){
        if (Units.IMPERIAL.equals(units)){
            return roundToFormat(altitude.inMiles()) + " miles";
        }
        return roundToFormat(altitude.inKilometers()) + " km";
    }
}
