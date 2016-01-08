package de.due.ldsa.db;

/*
    References:

    Cassandra basics:
    http://www.tutorialspoint.com/cassandra/

    Persistence with Cassandra:
    https://docs.datastax.com/en/developer/java-driver/2.1/java-driver/reference/crudOperations.html
 */

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Truncate;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import de.due.ldsa.db.codecs.*;
import de.due.ldsa.db.model.*;

import java.io.Closeable;
import java.io.IOException;

public class DatabaseImpl implements Database, Closeable
{
    static DatabaseImpl singleton;
    static Session session;
    Mapper<SocialNetwork> socialNetworkMapper;
    Mapper<ProfileFeed> profileFeedMapper;
    Mapper<Media> mediaMapper;
    Mapper<LocationImpl> locationMapper;
    Mapper<OrganisationPlace> organisationPlaceMapper;
    Mapper<CoopProfile> coopProfileMapper;
    Mapper<HumanProfile> humanProfileMapper;
    Mapper<Event> eventMapper;
    Mapper<Comment> commentMapper;
    Mapper<SocialNetworkInterestImpl> interestMapper;

    @Override
    public void close() throws IOException {
        session.close();
    }

    private DatabaseImpl()
    {
        //TODO: don't hardcode "127.0.0.1" - make this loadable from somewhere...
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect("ldsa");

        CodecRegistry registry = cluster.getConfiguration().getCodecRegistry();
        registry.register(new OffsetDateTimeCodec());
        registry.register(new LongArrayListCodec());
        registry.register(new StringArrayListCodec());
        registry.register(new ByteArrayCodec());
        registry.register(new UrlCodec());
        registry.register(new LocalDateCodec());
        registry.register(new RelationshipStatusCodec());
        registry.register(new SexCodec());
        registry.register(new InterestKindCodec());
        registry.register(new InterestKindArrayListCodec());
        registry.register(new UrlArrayListCodec());
    }

    public static Database getInstance()
    {
        if (singleton == null)
        {
            singleton = new DatabaseImpl();
        }
        return singleton;
    }

    /**
     * This method is intended for testing purposes.
     * You probably do not want to call this, because
     * it deletes ALL the contents of a table.
     * @param tName The name of the table you want to delete
     */
    public void truncateTable(String tName)
    {
        Truncate t = QueryBuilder.truncate("ldsa",tName);
        session.execute(t);
    }

    public void saveSocialNetwork(SocialNetwork sn)
    {
        if (socialNetworkMapper == null)
        {
            socialNetworkMapper = new MappingManager(session).mapper(SocialNetwork.class);
        }

        socialNetworkMapper.save(sn);
    }

    public SocialNetwork getSocialNetwork(int i)
    {
        if (socialNetworkMapper == null)
        {
            socialNetworkMapper = new MappingManager(session).mapper(SocialNetwork.class);
        }

        SocialNetwork result = socialNetworkMapper.get(i);
        return result;
    }

    public void saveProfileFeed(ProfileFeed pf)
    {
        if (profileFeedMapper == null)
        {
            profileFeedMapper = new MappingManager(session).mapper(ProfileFeed.class);
        }

        profileFeedMapper.save(pf);

    }

    public ProfileFeed getProfileFeed(long id)
    {
        if (profileFeedMapper == null)
        {
            profileFeedMapper = new MappingManager(session).mapper(ProfileFeed.class);
        }

        ProfileFeed result = profileFeedMapper.get(id);
        return result;
    }

    public void saveMedia(Media m)
    {
        if (mediaMapper == null)
        {
            mediaMapper = new MappingManager(session).mapper(Media.class);
        }

        mediaMapper.save(m);
    }

    public Media getMedia(long id)
    {
        if (mediaMapper == null)
        {
            mediaMapper = new MappingManager(session).mapper(Media.class);
        }

        Media result = mediaMapper.get(id);
        return result;
    }

    public void saveLocation(LocationImpl l)
    {
        if (locationMapper == null)
        {
            locationMapper = new MappingManager(session).mapper(LocationImpl.class);
        }

        locationMapper.save(l);
    }

    public LocationImpl getLocation(long id)
    {
        if (locationMapper == null)
        {
            locationMapper = new MappingManager(session).mapper(LocationImpl.class);
        }

        LocationImpl result = locationMapper.get(id);
        return result;
    }

    public void saveOrganisationPlace(OrganisationPlace op)
    {
        if (organisationPlaceMapper == null)
        {
            organisationPlaceMapper = new MappingManager(session).mapper(OrganisationPlace.class);
        }

        organisationPlaceMapper.save(op);
    }

    public OrganisationPlace getOrganisationPlace(long id)
    {
        if (organisationPlaceMapper == null)
        {
            organisationPlaceMapper = new MappingManager(session).mapper(OrganisationPlace.class);
        }

        OrganisationPlace result = organisationPlaceMapper.get(id);
        return result;
    }

    /**
     * Writes a Company Profile to the database.
     *
     * @param cp The Company Profile you want to persist.
     */
    public void saveCoopProfile(CoopProfile cp)
            throws DbException {
        if (getHumanProfile(cp.getId()) != null) {
            //This needs to be done because Human IDs and Company IDs share the same number sequence.
            throw new DbException("The ID specified in that company profile is already used by a human ID:" +
                    new Long(cp.getId()).toString());
        }

        if (coopProfileMapper == null)
        {
            coopProfileMapper = new MappingManager(session).mapper(CoopProfile.class);
        }

        cp.prepareSave();
        coopProfileMapper.save(cp);
    }

    public CoopProfile getCoopProfile(long id)
    {
        if (coopProfileMapper == null)
        {
            coopProfileMapper = new MappingManager(session).mapper(CoopProfile.class);
        }

        CoopProfile result = coopProfileMapper.get(id);
        return result;
    }

    public void saveHumanProfile(HumanProfile hp)
            throws DbException {
        if (getCoopProfile(hp.getId()) != null) {
            //This needs to be done because Human IDs and Company IDs share the same number sequence.
            throw new DbException("The ID specified in that company profile is already used by a company ID: "
                    + new Long(hp.getId()).toString());
        }

        if (humanProfileMapper == null)
        {
            humanProfileMapper = new MappingManager(session).mapper(HumanProfile.class);
        }

        hp.prepareSave();
        humanProfileMapper.save(hp);
    }

    public HumanProfile getHumanProfile(long id)
    {
        if (humanProfileMapper == null)
        {
            humanProfileMapper = new MappingManager(session).mapper(HumanProfile.class);
        }

        HumanProfile result = humanProfileMapper.get(id);
        return result;
    }

    public void saveEvent(Event id)
    {
        if (eventMapper == null)
        {
            eventMapper = new MappingManager(session).mapper(Event.class);
        }

        eventMapper.save(id);
    }

    public Event getEvent(long id)
    {
        if (eventMapper == null)
        {
            eventMapper = new MappingManager(session).mapper(Event.class);
        }

        Event event = eventMapper.get(id);
        return event;
    }

    public void saveComment(Comment c)
    {
        if (commentMapper == null)
        {
            commentMapper = new MappingManager(session).mapper(Comment.class);
        }

        c.prepareSave();
        commentMapper.save(c);
    }

    public Comment getComment(long id)
    {
        if (commentMapper == null)
        {
            commentMapper = new MappingManager(session).mapper(Comment.class);
        }

        Comment comment = commentMapper.get(id);
        return comment;
    }

    public void saveInterest(SocialNetworkInterestImpl socialNetworkInterest)
    {
        if (interestMapper == null)
        {
            interestMapper = new MappingManager(session).mapper(SocialNetworkInterestImpl.class);
        }

        interestMapper.save(socialNetworkInterest);
    }

    public SocialNetworkInterestImpl getInterest(long id)
    {
        if (interestMapper == null)
        {
            interestMapper = new MappingManager(session).mapper(SocialNetworkInterestImpl.class);
        }

        SocialNetworkInterestImpl result = interestMapper.get(id);
        return result;
    }

    public boolean isHuman(long id)
            throws DbException {
        HumanProfile hp = getHumanProfile(id);
        if (hp != null) return true;

        CoopProfile cp = getCoopProfile(id);
        if (cp != null) return false;

        throw new DbException("The specified ID does not exist:" + new Long(id).toString());
    }

    /**
     * Use this to figure out the ID of a new CoopProfile or HumanProfile.
     *
     * @return A free ID
     * @throws DbException
     */
    public long getNextProfileId()
            throws DbException {
        //We have not found a way to do this in a mapper-way, therefore we absolutely need to use a hand-crafted command
        //here. The alternative way would be keeping a seperate Table which keeps tracks of the highest IDs in each
        //table, but we were unsure if this wouldn't be too slow.

        ResultSet rs1 = session.execute("SELECT MAX(id) FROM coopProfiles");
        long amount1 = rs1.one().getLong(0);

        ResultSet rs2 = session.execute("SELECT MAX(id) FROM humanProfiles");
        long amount2 = rs2.one().getLong(0);

        return Math.max(amount1, amount2) + 1;
    }

    public long getNextMediaId()
            throws DbException {
        ResultSet rs1 = session.execute("SELECT MAX(id) FROM media");
        return rs1.one().getLong(0) + 1;
    }

    public long getNextCommentId()
            throws DbException {
        ResultSet rs1 = session.execute("SELECT MAX(id) FROM comments");
        return rs1.one().getLong(0) + 1;
    }

    public long getNextInterestId()
            throws DbException {
        ResultSet rs1 = session.execute("SELECT MAX(id) FROM interests");
        return rs1.one().getLong(0) + 1;
    }

    public long getNextProfileFeedId()
            throws DbException {
        ResultSet rs1 = session.execute("SELECT MAX(id) FROM profileFeeds");
        return rs1.one().getLong(0) + 1;
    }

    public long getNextLocationId()
            throws DbException {
        ResultSet rs1 = session.execute("SELECT MAX(id) FROM locations");
        return rs1.one().getLong(0) + 1;
    }
}