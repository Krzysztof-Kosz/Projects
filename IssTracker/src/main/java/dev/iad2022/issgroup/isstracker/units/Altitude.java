package dev.iad2022.issgroup.isstracker.units;

public class Altitude {
    private final double altitudeInKilometers;

    public Altitude(double altitudeInKilometers) {
        this.altitudeInKilometers = altitudeInKilometers;
    }

    public double inKilometers(){
        return this.altitudeInKilometers;
    }

    public double inMiles(){
        return 0.6213712 * this.altitudeInKilometers;
    }
}
