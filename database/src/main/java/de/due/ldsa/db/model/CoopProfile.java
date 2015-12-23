package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.time.LocalDate;

/**
 *
 */
public class CoopProfile extends Profile
{
    LocalDate dateFounded;

    public int countInteraction(Profile p)
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public double countAverageInteractionPerFeed(Profile p)
            throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public double getAverageInteractionPerFeed()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public double getAverageOfActionsPerDay()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }
}
