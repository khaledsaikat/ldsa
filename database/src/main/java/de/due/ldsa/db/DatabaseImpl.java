package de.due.ldsa.db;

/*
    References:

    Cassandra basics:
    http://www.tutorialspoint.com/cassandra/

    Persistence with Cassandra:
    https://docs.datastax.com/en/developer/java-driver/2.1/java-driver/reference/crudOperations.html
 */
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.CodecRegistry;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Truncate;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import de.due.ldsa.db.codecs.ByteArrayCodec;
import de.due.ldsa.db.codecs.LongArrayListCodec;
import de.due.ldsa.db.codecs.OffsetDateTimeCodec;
import de.due.ldsa.db.codecs.StringArrayListCodec;
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

    @Override
    public void close() throws IOException {
        session.close();
    }

    private DatabaseImpl()
    {
        //TODO: don't hardcode "127.0.0.1" - make this loadable from somewhere...
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect();

        CodecRegistry registry = cluster.getConfiguration().getCodecRegistry();
        registry.register(new OffsetDateTimeCodec());
        registry.register(new LongArrayListCodec());
        registry.register(new StringArrayListCodec());
        registry.register(new ByteArrayCodec());
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
}