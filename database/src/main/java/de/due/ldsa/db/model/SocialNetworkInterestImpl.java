package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.util.ArrayList;

/**
 *
 */
public class SocialNetworkInterestImpl implements SocialNetworkInterest {
    ArrayList<InterestKind> interestKinds;

    @Override
    public void addInterestKind(InterestKind ik)
            throws DbException
    {
        throw new DbException("not yet implemented");
    }

    @Override
    public void removeInterestKind(InterestKind ik)
            throws DbException
    {
        throw new DbException("not yet implemented");
    }

    @Override
    public boolean isInterestKind(InterestKind ik)
    {
        for(InterestKind e: interestKinds)
        {
            if (e.equals(ik))
                return true;
        }
        return false;
    }
}
