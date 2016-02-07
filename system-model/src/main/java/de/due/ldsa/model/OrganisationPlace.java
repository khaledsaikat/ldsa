package de.due.ldsa.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import de.due.ldsa.ModelUtils;
import de.due.ldsa.exception.ModelException;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * Author: Romina (scrobart)
 *
 */

// We know this should extend LocationImpl, but we can't do that, because the
// Cassandra driver does not support inheritance.
@Table(keyspace = "ldsa", name = "organisationPlaces")
public class OrganisationPlace extends SocialNetworkContentImpl implements Location, Serializable {
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
	@Column(name = "organisationProfileId")
	public long organisationProfileId;

	@Column(name = "interestKinds")
	ArrayList<InterestKind> interestKinds;

	@Override
	public int getSocialNetworkId() {
		return socialNetworkId;
	}

	@Override
	public void setSocialNetworkId(int socialNetworkId) {
		this.socialNetworkId = socialNetworkId;
	}

	@Override
	public OffsetDateTime getContentTimestamp() {
		return contentTimestamp;
	}

	@Override
	public void setContentTimestamp(OffsetDateTime contentTimestamp) {
		this.contentTimestamp = contentTimestamp;
	}

	@Override
	public OffsetDateTime getCrawlingTimestamp() {
		return crawlingTimestamp;
	}

	@Override
	public void setCrawlingTimestamp(OffsetDateTime crawlingTimestamp) {
		this.crawlingTimestamp = crawlingTimestamp;
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
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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
	public long getIsInId() {
		return isInId;
	}

	@Override
	public void setIsInId(long isInId) {
		this.isInId = isInId;
	}

	public long getOrganisationProfileId() {
		return organisationProfileId;
	}

	public void setOrganisationProfileId(long organisationProfileId) {
		this.organisationProfileId = organisationProfileId;
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
	public CoopProfile getCoopProfile() throws ModelException {
		throw new ModelException("not yet implemented.");
	}

	@Override
	public Position getPosition() {
		return new Position(this.positionLatidue, this.positionLongitude);
	}

	@Override
	public void setPosition(Position p) {
		this.positionLatidue = p.getLatidue();
		this.positionLongitude = p.getLongitude();
	}

	@Override
	public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws ModelException {
		this.contentTimestamp = content;
		this.crawlingTimestamp = crawling;
		this.socialNetworkId = sn.getId();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof OrganisationPlace))
			return false;

		OrganisationPlace that = (OrganisationPlace) o;

		if (socialNetworkId != that.socialNetworkId)
			return false;
		if (id != that.id)
			return false;
		if (Double.compare(that.positionLatidue, positionLatidue) != 0)
			return false;
		if (Double.compare(that.positionLongitude, positionLongitude) != 0)
			return false;
		if (isInId != that.isInId)
			return false;
		if (organisationProfileId != that.organisationProfileId)
			return false;
		if (contentTimestamp != null ? !contentTimestamp.equals(that.contentTimestamp) : that.contentTimestamp != null)
			return false;
		return !(crawlingTimestamp != null ? !crawlingTimestamp.equals(that.crawlingTimestamp)
				: that.crawlingTimestamp != null) && name.equals(that.name) && !(city != null ? !city.equals(that.city) : that.city != null) && !(country != null ? !country.equals(that.country) : that.country != null);

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
		result = 31 * result + (int) (organisationProfileId ^ (organisationProfileId >>> 32));
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
