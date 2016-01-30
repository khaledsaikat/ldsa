package de.due.ldsa.model;


import java.io.Serializable;
import java.util.ArrayList;

import de.due.ldsa.exception.DbException;

/**
 * Author: Romina (scrobart)
 *
 */
public interface SocialNetworkInterest {
    void addInterestKind(InterestKind ik);

    void removeInterestKind(InterestKind ik);
    boolean isInterestKind(InterestKind ik);

    ArrayList<InterestKind> getInterestKinds();

    boolean checkValidInterestKinds();
}
