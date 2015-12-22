package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.util.ArrayList;

/**
 *
 */
public class Hashtag {


    private String title;
    private ArrayList<SocialNetworkContent> usedAtList;

    public ArrayList<SocialNetworkContent> getUsedAtList() {
        return usedAtList;
    }

    public String getTitle() {
        return title;
    }

    public long getTimesUsed()
            throws DbException
    {
        throw new DbException("not yet implemented");
    }
}
