package de.due.ldsa.db.model;

import java.util.ArrayList;

/**
 *
 */
public class Event extends SocialNetworkContentImpl
{
    public String name;
    public ArrayList<Profile> hosts;
    public Location location;
    public ArrayList<Profile> invited;
    public ArrayList<Profile> attending;
    public String eventText;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
