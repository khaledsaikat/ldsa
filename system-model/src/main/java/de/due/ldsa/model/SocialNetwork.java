package de.due.ldsa.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.google.gson.Gson;

import de.due.ldsa.exception.DbException;

import java.io.Serializable;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 *
 */
@Table(keyspace = "ldsa", name = "socialNetworks")
public class SocialNetwork implements Serializable {
	@PartitionKey
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "homeURL")
	private URL homeURL;

	// TODO: Turn logo to image
	@Column(name = "logo")
	private byte[] logo;

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

	public URL getHomeURL() {
		return homeURL;
	}

	public void setHomeURL(URL homeURL) {
		this.homeURL = homeURL;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// Complex methods
	// ------------------------------------------------------------------------------------------------------------------
	public Iterable<Profile> allProfiles() throws DbException {
		throw new DbException("not yet implemented.");
	}

	public Iterable<ProfileFeed> allProfileFeed() throws DbException {
		throw new DbException("not yet implemented.");
	}

	public Iterable<Media> allMedia() throws DbException {
		throw new DbException("not yet implemented.");
	}

	public Iterable<SocialNetworkContent> allContent() throws DbException {
		throw new DbException("not yet implemented.");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof SocialNetwork))
			return false;

		SocialNetwork that = (SocialNetwork) o;

		if (id != that.id)
			return false;
		if (!name.equals(that.name))
			return false;
		if (homeURL != null ? !homeURL.equals(that.homeURL) : that.homeURL != null)
			return false;
		return Arrays.equals(logo, that.logo);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + name.hashCode();
		result = 31 * result + (homeURL != null ? homeURL.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(logo);
		return result;
	}

	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}	
}
