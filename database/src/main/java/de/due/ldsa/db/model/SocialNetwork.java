package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import de.due.ldsa.db.DbException;

import java.nio.ByteBuffer;

/**
 *
 */
@Table(keyspace = "ldsa", name = "socialNetworks")
public class SocialNetwork
{
    @PartitionKey
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "homeURL")
    private String homeURL;

    @Column(name = "logo")
    private ByteBuffer logo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeURL() {
        return homeURL;
    }

    public void setHomeURL(String homeURL) {
        this.homeURL = homeURL;
    }

    public ByteBuffer getLogo() {
        return logo;
    }

    public void setLogo(ByteBuffer logo) {
        this.logo = logo;
    }

    public Iterable<Profile> allProfiles()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public Iterable<ProfileFeed> allProfileFeed()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public Iterable<Media> allMedia()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public Iterable<SocialNetworkContent> allContent()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }
}
