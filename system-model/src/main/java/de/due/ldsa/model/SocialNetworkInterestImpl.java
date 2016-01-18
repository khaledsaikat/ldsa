package de.due.ldsa.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.google.gson.Gson;

import de.due.ldsa.exception.DbException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 */
@Table(keyspace = "ldsa", name = "interests")
public class SocialNetworkInterestImpl implements SocialNetworkInterest, Serializable {
	@PartitionKey
	long id;
	@Column(name = "kinds")
	ArrayList<InterestKind> interestKinds;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ArrayList<InterestKind> getInterestKinds() {
		return interestKinds;
	}

	public void setInterestKinds(ArrayList<InterestKind> interestKinds) {
		this.interestKinds = interestKinds;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// COMPLEX METHODS
	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public void addInterestKind(InterestKind ik) throws DbException {
		if (interestKinds == null) {
			interestKinds = new ArrayList<InterestKind>();
		}
		interestKinds.add(ik);
	}

	@Override
	public void removeInterestKind(InterestKind ik) throws DbException {
		throw new DbException("not yet implemented");
	}

	@Override
	public boolean isInterestKind(InterestKind ik) throws DbException {
		throw new DbException("not yet implemented");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof SocialNetworkInterestImpl))
			return false;

		SocialNetworkInterestImpl that = (SocialNetworkInterestImpl) o;

		if (id != that.id)
			return false;
		return !(interestKinds != null ? !interestKinds.equals(that.interestKinds) : that.interestKinds != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (interestKinds != null ? interestKinds.hashCode() : 0);
		return result;
	}

	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
