package de.due.ldsa.db.model;

/**
 *
 */
public class OrganisationPlace extends Location
{
    public CoopProfile organisation;

    public CoopProfile getOrganisation()
    {
        return organisation;
    }
}
