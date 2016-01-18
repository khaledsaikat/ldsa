package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.io.Serializable;

/**
 * Author: Romina (scrobart)
 *
 */
public interface SocialNetworkInterest extends Serializable {
    void addInterestKind(InterestKind ik)
            throws DbException;

    void removeInterestKind(InterestKind ik)
                    throws DbException;

    boolean isInterestKind(InterestKind ik);

    long getId();

    void setId(long id);
}
