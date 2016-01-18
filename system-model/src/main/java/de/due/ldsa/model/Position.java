package de.due.ldsa.model;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 *
 */
public class Position implements Serializable
{
    //TODO: add reference to Location
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (Double.compare(position.latidue, latidue) != 0) return false;
        return Double.compare(position.longitude, longitude) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latidue);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    
    public String getJsonString(){
    	Gson gson = new Gson();
    	return gson.toJson(this);
    }
}
