package dev.iad2022.issgroup.isstracker.units;

public class Speed {
    private final double speedInKmPerHour;

    public Speed(double speedInKmPerHour){
        this.speedInKmPerHour = speedInKmPerHour;
    }

    public double inKilometersPerHour(){
        return this.speedInKmPerHour;
    }

    public double inMilesPerHour(){
        return 0.6213712 * this.speedInKmPerHour;
    }

    public double inMetersPerSecond(){
        return this.speedInKmPerHour / 3.6;
    }

    public double inFeetPerSecond(){
        return this.speedInKmPerHour * 0.911;
    }
}
