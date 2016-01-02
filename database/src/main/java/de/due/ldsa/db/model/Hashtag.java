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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hashtag)) return false;

        Hashtag hashtag = (Hashtag) o;

        return title.equals(hashtag.title);

    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
