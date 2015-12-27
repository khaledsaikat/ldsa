package de.due.ldsa.db;

import de.due.ldsa.db.model.SocialNetwork;

/**
 * Created by firas on 10.12.15.
 */
public interface Database
{
    void truncateTable(String tName);
    void saveSocialNetwork(SocialNetwork sn);
}
