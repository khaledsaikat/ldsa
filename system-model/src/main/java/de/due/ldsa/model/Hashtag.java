package de.due.ldsa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import de.due.ldsa.exception.DbException;

/**
 * Hashtag for social data. To get this object, fetch it from a comment or feed.
 * 
 * @author: Romina (scrobart)
 * 
 */
@Table(keyspace = "ldsa", name = "hashtags")
public class Hashtag implements Serializable {
	@Column(name = "title")
	@PartitionKey
	private String title;
	@Transient
	private transient List<SocialNetworkContent> usedAtList;

	public Hashtag() {
	}

	public Hashtag(String name) {
		this.title = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Hashtag))
			return false;

		Hashtag hashtag = (Hashtag) o;

		return title.equals(hashtag.title);

	}

	@Override
	public String toString() {
		return "Hashtag{" +
				"title='" + title + '\'' +
				'}';
	}

	@Override
	public int hashCode() {
		return title.hashCode();
	}

	public List<SocialNetworkContent> getUsedAtList() throws DbException {
		return usedAtList;
	}

	/**
	 * Sets the "Used-at" List. The Database is supposed to call this.
	 *
	 * @param usedAtList A list of all contents that somehow refer to this hashtag.
	 */
	public void setUsedAtList(List<SocialNetworkContent> usedAtList) {
		this.usedAtList = usedAtList;
	}

	public long getTimesUsed() throws DbException {
		if (usedAtList == null) {
			throw new DbException("Please use setUsedAtList before calling getTimesUsed");
		}
		return usedAtList.size();
	}
}
