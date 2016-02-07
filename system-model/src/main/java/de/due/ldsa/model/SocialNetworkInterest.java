package de.due.ldsa.model;


import java.util.ArrayList;

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
