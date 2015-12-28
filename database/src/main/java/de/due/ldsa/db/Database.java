package de.due.ldsa.db;

import com.datastax.driver.mapping.MappingManager;
import de.due.ldsa.db.model.ProfileFeed;
import de.due.ldsa.db.model.SocialNetwork;

/**
 * Created by firas on 10.12.15.
 */
public interface Database
{
    void truncateTable(String tName);
    void saveSocialNetwork(SocialNetwork sn);
    SocialNetwork getSocialNetwork(int id);
    void saveProfileFeed(ProfileFeed pf);
    ProfileFeed getProfileFeed(long id);
}
