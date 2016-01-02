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
    void saveOrganisationPlace(OrganisationPlace op);
    OrganisationPlace getOrganisationPlace(long id);
    void saveCoopProfile(CoopProfile cp);
    CoopProfile getCoopProfile(long id);
    void saveHumanProfile(HumanProfile hp);
    HumanProfile getHumanProfile(long id);
    void saveEvent(Event id);
    Event getEvent(long id);
    void saveComment(Comment c);
    Comment getComment(long id);
    void saveInterest(SocialNetworkInterestImpl socialNetworkInterest);
    SocialNetworkInterestImpl getInterest(long id);
}
