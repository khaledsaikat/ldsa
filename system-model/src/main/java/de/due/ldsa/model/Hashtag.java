package de.due.ldsa.model;

import java.io.Serializable;
import java.util.ArrayList;
import com.datastax.driver.mapping.annotations.Column;
import de.due.ldsa.exception.DbException;

/**
 * Author: Romina (scrobart)
 *
 * To get this object, fetch it from a comment or feed.
 */
public class Hashtag implements Serializable {
	@Column(name = "title")
	private String title;
	@Column(name = "usedAtList")
	private ArrayList<SocialNetworkContent> usedAtList;

	public Hashtag() {
	}

	public Hashtag(String name) {
		this.title = name;
	}

	public String getTitle() {
		return title;
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
	public int hashCode() {
		return title.hashCode();
	}

	public ArrayList<SocialNetworkContent> getUsedAtList() throws DbException {
		return usedAtList;
	}

	public void setUsedAtList(ArrayList<SocialNetworkContent> usedAtList) {
		this.usedAtList = usedAtList;
	}

}
