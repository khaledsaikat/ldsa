package de.due.ldsa.model;

import de.due.ldsa.exception.DbException;

/**
 *
 */
public interface SocialNetworkInterest {
    void addInterestKind(InterestKind ik)
            throws DbException;

    void removeInterestKind(InterestKind ik)
                    throws DbException;

    boolean isInterestKind(InterestKind ik);

    public long getId();

    public void setId(long id);
}
