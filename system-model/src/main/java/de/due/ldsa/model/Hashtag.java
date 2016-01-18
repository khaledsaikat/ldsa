package de.due.ldsa.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;

import de.due.ldsa.exception.DbException;

/**
 *
 */
public class Hashtag implements Serializable {

	private String title;
	private ArrayList<SocialNetworkContent> usedAtList;

	public String getTitle() {
		return title;
	}

	public ArrayList<SocialNetworkContent> getUsedAtList() throws DbException {
		throw new DbException("not yet implemented");
	}

	public long getTimesUsed() throws DbException {
		throw new DbException("not yet implemented");
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

	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
