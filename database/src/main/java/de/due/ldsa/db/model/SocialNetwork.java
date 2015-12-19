package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 */
public class SocialNetwork
{
    public String name;
    public URL homeURL;
    public Image logo;

    public ArrayList<Profile> allProfiles()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public ArrayList<ProfileFeed> allProfileFeed()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public ArrayList<Media> allMedia()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public ArrayList<SocialNetworkContent> allContent()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }
}
