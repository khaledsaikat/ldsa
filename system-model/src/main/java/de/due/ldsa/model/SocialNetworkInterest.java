package de.due.ldsa.model;


import java.io.Serializable;

import de.due.ldsa.exception.DbException;

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
