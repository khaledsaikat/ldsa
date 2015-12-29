package de.due.ldsa.db.model;

/**
 *
 */
public class Position
{
    public Position() {

    }

    public Position(double latidue, double longitude) {
        this.latidue = latidue;
        this.longitude = longitude;
    }

    private double latidue;
    private double longitude;

    public double getLatidue() {
        return latidue;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatidue(double latidue) {
        this.latidue = latidue;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
