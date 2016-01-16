package de.due.ldsa.db;

import com.datastax.driver.mapping.MappingManager;
import de.due.ldsa.db.model.*;

/**
 * Created by firas on 10.12.15.
 */
public interface Database
{
    void truncateTable(String tName);
    void saveSocialNetwork(SocialNetwork sn) throws DbException;
    SocialNetwork getSocialNetwork(int id);
    void saveProfileFeed(ProfileFeed pf) throws DbException;
    ProfileFeed getProfileFeed(long id);
    void saveMedia(Media m) throws DbException;
    Media getMedia(long id);
    void saveLocation(LocationImpl l) throws DbException;
    LocationImpl getLocation(long id);
    void saveOrganisationPlace(OrganisationPlace op) throws DbException;
    OrganisationPlace getOrganisationPlace(long id);
    void saveCoopProfile(CoopProfile cp) throws DbException;
    CoopProfile getCoopProfile(long id);
    void saveHumanProfile(HumanProfile hp) throws DbException;
    HumanProfile getHumanProfile(long id);
    void saveEvent(Event id) throws DbException;
    Event getEvent(long id);
    void saveComment(Comment c) throws DbException;
    Comment getComment(long id);
    void saveInterest(SocialNetworkInterestImpl socialNetworkInterest) throws DbException;
    SocialNetworkInterestImpl getInterest(long id);
    boolean isHuman(long id) throws DbException;
    long getNextProfileId() throws DbException;
    long getNextMediaId() throws DbException;
    long getNextCommentId() throws DbException;
    long getNextInterestId() throws DbException;
    long getNextProfileFeedId() throws DbException;
    long getNextLocationId() throws DbException;

    public Profile autoGetProfile(long id) throws DbException;

    public void autoSaveProfile(Profile p) throws DbException;
}
