package net.sf.openrocket.models.wind;

import net.sf.openrocket.util.Coordinate;

public class PowerLawWindModel implements WindModel {

    private double refWindSpeed;
    private double refWindDirection;
    private double refAlt;
    private double surfaceC;

    public PowerLawWindModel(
        double refWindSpeed, double refWindDirection,
        double refAlt, double surfaceC) {
        this.refWindSpeed = refWindSpeed;
        this.refWindDirection = refWindDirection;
        this.refAlt = refAlt;
        this.surfaceC = surfaceC;
    }

    @Override
    public Coordinate getWindVelocity(double time, double altitude) {
        if (altitude <= 0) return new Coordinate(0, 0, 0);
        double speed = refWindSpeed * Math.pow(altitude / refAlt, 1 / surfaceC);
        // return new Coordinate(speed, 0, 0);
        return new Coordinate(speed * Math.sin(refWindDirection),
                              speed * Math.cos(refWindDirection), 0);
    }
    @Override
    public int getModID() {
        return (int) (refAlt * 100000 + surfaceC * 100);
    }
}
