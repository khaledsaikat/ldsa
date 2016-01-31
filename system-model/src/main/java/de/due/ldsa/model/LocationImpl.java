package de.due.ldsa.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;

import de.due.ldsa.ModelUtils;
import de.due.ldsa.exception.DbException;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * Author: Romina (scrobart)
 *
 * If you need to serialize this, please use a serializer that honors transient
 * fields.
 */
@Table(keyspace = "ldsa", name = "locations")
public class LocationImpl extends SocialNetworkContentImpl implements Location, Serializable {
	/*
	 * This needs to be put right here, because Datastax' Cassandra mapper does
	 * not support inheritance. If you need access to these fields use the
	 * getters and setters from the upper classes.
	 */
	@Column(name = "snId")
	int socialNetworkId;
	@Column(name = "contentTimestamp")
	OffsetDateTime contentTimestamp;
	@Column(name = "crawlingTimestamp")
	OffsetDateTime crawlingTimestamp;

	@PartitionKey
	public long id;
	@Column(name = "name")
	public String name;
	@Column(name = "city")
	public String city;
	@Column(name = "country")
	public String country;
	@Column(name = "latidue")
	public double positionLatidue;
	@Column(name = "longitude")
	public double positionLongitude;
	@Column(name = "isInId")
	public long isInId;
	@Column(name = "interestKinds")
	ArrayList<InterestKind> interestKinds;

	// ------------------------------------------------------------------------------------------------------------------
	// Getters and setters
	// ------------------------------------------------------------------------------------------------------------------

	@Override
	public long getIsInId() {
		return isInId;
	}

	@Override
	public void setIsInId(long isInId) {
		this.isInId = isInId;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String getCountry() {
		return country;
	}

	@Override
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public double getPositionLatidue() {
		return positionLatidue;
	}

	@Override
	public void setPositionLatidue(double positionLatidue) {
		this.positionLatidue = positionLatidue;
	}

	@Override
	public double getPositionLongitude() {
		return positionLongitude;
	}

	@Override
	public void setPositionLongitude(double positionLongitude) {
		this.positionLongitude = positionLongitude;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getSocialNetworkId() {
		return socialNetworkId;
	}

	@Override
	public void setSocialNetworkId(int socialNetworkId) {
		this.socialNetworkId = socialNetworkId;
	}

	@Override
	public void setContentTimestamp(OffsetDateTime contentTimestamp) {
		this.contentTimestamp = contentTimestamp;
	}

	@Override
	public void setCrawlingTimestamp(OffsetDateTime crawlingTimestamp) {
		this.crawlingTimestamp = crawlingTimestamp;
	}

	public ArrayList<InterestKind> getInterestKinds() {
		return interestKinds;
	}

	public void setInterestKinds(ArrayList<InterestKind> interestKinds) {
		this.interestKinds = interestKinds;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// Complex methods
	// ------------------------------------------------------------------------------------------------------------------
	@Transient
	transient Integer timesUsed;

	@Override
	public OffsetDateTime getContentTimestamp() throws DbException {
		return contentTimestamp;
	}

	@Override
	public OffsetDateTime getCrawlingTimestamp() throws DbException {
		return crawlingTimestamp;
	}

	@Override
	public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws DbException {
		this.contentTimestamp = content;
		this.crawlingTimestamp = crawling;
		this.socialNetworkId = sn.getId();
	}

	@Override
	public Position getPosition() {
		return new Position(positionLongitude, positionLatidue);
	}

	@Override
	public void setPosition(Position p) {
		this.positionLatidue = p.getLatidue();
		this.positionLongitude = p.getLongitude();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof LocationImpl))
			return false;

		LocationImpl location = (LocationImpl) o;

		if (socialNetworkId != location.socialNetworkId)
			return false;
		if (id != location.id)
			return false;
		if (Double.compare(location.positionLatidue, positionLatidue) != 0)
			return false;
		if (Double.compare(location.positionLongitude, positionLongitude) != 0)
			return false;
		if (isInId != location.isInId)
			return false;
		if (contentTimestamp != null ? !contentTimestamp.equals(location.contentTimestamp)
				: location.contentTimestamp != null)
			return false;
		if (crawlingTimestamp != null ? !crawlingTimestamp.equals(location.crawlingTimestamp)
				: location.crawlingTimestamp != null)
			return false;
		if (!name.equals(location.name))
			return false;
		if (city != null ? !city.equals(location.city) : location.city != null)
			return false;
		return !(country != null ? !country.equals(location.country) : location.country != null);

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = socialNetworkId;
		result = 31 * result + (contentTimestamp != null ? contentTimestamp.hashCode() : 0);
		result = 31 * result + (crawlingTimestamp != null ? crawlingTimestamp.hashCode() : 0);
		result = 31 * result + (int) (id ^ (id >>> 32));
		result = 31 * result + name.hashCode();
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (country != null ? country.hashCode() : 0);
		temp = Double.doubleToLongBits(positionLatidue);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(positionLongitude);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (int) (isInId ^ (isInId >>> 32));
		return result;
	}

	@Override
	public void addInterestKind(InterestKind ik) {
		if (interestKinds == null) {
			interestKinds = new ArrayList<>();
		}
		ModelUtils.addInterestKind(interestKinds, ik);
	}

	@Override
	public void removeInterestKind(InterestKind ik) {
		if (interestKinds == null) {
			interestKinds = new ArrayList<>();
		}
		ModelUtils.removeInterestKind(interestKinds, ik);
	}

	@Override
	public boolean isInterestKind(InterestKind ik) {
		if (interestKinds == null) {
			interestKinds = new ArrayList<>();
		}
		return interestKinds.contains(ik);
	}

	@Override
	public boolean checkValidInterestKinds() {
		return ModelUtils.checkValidInterestKinds(interestKinds);
	}
}
