package de.due.ldsa.db;

import com.datastax.driver.mapping.MappingManager;
import de.due.ldsa.db.model.*;

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
    void saveMedia(Media m);
    Media getMedia(long id);
    void saveLocation(LocationImpl l);
    LocationImpl getLocation(long id);
    public void saveOrganisationPlace(OrganisationPlace op);
    public OrganisationPlace getOrganisationPlace(long id);
}
