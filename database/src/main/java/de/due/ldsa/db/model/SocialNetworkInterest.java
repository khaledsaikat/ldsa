package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

/**
 *
 */
public interface SocialNetworkInterest {
    void addInterestKind(InterestKind ik)
            throws DbException;

    void removeInterestKind(InterestKind ik)
                    throws DbException;

    boolean isInterestKind(InterestKind ik);
}
