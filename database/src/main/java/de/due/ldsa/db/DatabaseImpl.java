package de.due.ldsa.db;

/*
    References:

    Cassandra basics:
    http://www.tutorialspoint.com/cassandra/

    Persistence with Cassandra:
    https://docs.datastax.com/en/developer/java-driver/2.1/java-driver/reference/crudOperations.html
 */
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Truncate;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import de.due.ldsa.db.model.SocialNetwork;

import java.io.Closeable;
import java.io.IOException;

public class DatabaseImpl implements Database, Closeable
{
    static DatabaseImpl singleton;
    static Session session;
    Mapper<SocialNetwork> socialNetworkMapper;

    @Override
    public void close() throws IOException {
        session.close();
    }

    private DatabaseImpl()
    {
        //TODO: don't hardcode "127.0.0.1" - make this loadable from somewhere...
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect();

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


}