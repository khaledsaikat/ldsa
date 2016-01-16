package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Author: Romina (scrobart)
 *
 * Not Serializable. To get this object, fetch it from a comment or feed.
 */
public class Hashtag {
    public Hashtag() {
    }

    public Hashtag(String name) {
        this.title = name;
    }

    private String title;
    private ArrayList<SocialNetworkContent> usedAtList;

    public String getTitle() {
        return title;
    }

    public ArrayList<SocialNetworkContent> getUsedAtList()
            throws DbException {
        throw new DbException("not yet implemented");
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
