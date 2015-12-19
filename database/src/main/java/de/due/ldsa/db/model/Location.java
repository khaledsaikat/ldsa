package de.due.ldsa.db.model;

/**
 *
 */
public class Location extends SocialNetworkContentImpl
{
    public String name;
    public int timesUsed;
    public String city;
    public String country;
    public Position position;
    public Location isIn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimesUsed() {
        return timesUsed;
    }
}
