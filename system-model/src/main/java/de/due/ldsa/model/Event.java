package de.due.ldsa.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import de.due.ldsa.exception.DbException;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * Author: Romina (scrobart)
 *
 */
@Table(keyspace = "ldsa", name = "events")
public class Event extends SocialNetworkContentImpl implements Serializable {
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
	long id;

	@Column(name = "name")
	public String name;
	@Column(name = "hostIds")
	public ArrayList<Long> hostIds;
	@Column(name = "locationId")
	public long locationId;
	@Column(name = "invitedIds")
	public ArrayList<Long> invitedIds;
	@Column(name = "attendingIds")
	public ArrayList<Long> attendingIds;
	@Column(name = "eventText")
	public String eventText;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSocialNetworkId() {
		return socialNetworkId;
	}

	public void setSocialNetworkId(int socialNetworkId) {
		this.socialNetworkId = socialNetworkId;
	}

	public void setContentTimestamp(OffsetDateTime contentTimestamp) {
		this.contentTimestamp = contentTimestamp;
	}

	public void setCrawlingTimestamp(OffsetDateTime crawlingTimestamp) {
		this.crawlingTimestamp = crawlingTimestamp;
	}

	public OffsetDateTime getContentTimestamp() throws DbException {
		return contentTimestamp;
	}

	public OffsetDateTime getCrawlingTimestamp() throws DbException {
		return crawlingTimestamp;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public ArrayList<Long> getHostIds() {
		return hostIds;
	}

	public void setHostIds(ArrayList<Long> hostIds) {
		this.hostIds = hostIds;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public ArrayList<Long> getInvitedIds() {
		return invitedIds;
	}

	public void setInvitedIds(ArrayList<Long> invitedIds) {
		this.invitedIds = invitedIds;
	}

	public ArrayList<Long> getAttendingIds() {
		return attendingIds;
	}

	public void setAttendingIds(ArrayList<Long> attendingIds) {
		this.attendingIds = attendingIds;
	}

	public String getEventText() {
		return eventText;
	}

	public void setEventText(String eventText) {
		this.eventText = eventText;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// COMPLEX METHODS
	// ------------------------------------------------------------------------------------------------------------------

	public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws DbException {
		this.contentTimestamp = content;
		this.crawlingTimestamp = crawling;
		this.socialNetworkId = sn.getId();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Event))
			return false;

		Event event = (Event) o;

		if (socialNetworkId != event.socialNetworkId)
			return false;
		if (id != event.id)
			return false;
		if (locationId != event.locationId)
			return false;
		if (contentTimestamp != null ? !contentTimestamp.equals(event.contentTimestamp)
				: event.contentTimestamp != null)
			return false;
		if (crawlingTimestamp != null ? !crawlingTimestamp.equals(event.crawlingTimestamp)
				: event.crawlingTimestamp != null)
			return false;
		if (!name.equals(event.name))
			return false;
		if (hostIds != null ? !hostIds.equals(event.hostIds) : event.hostIds != null)
			return false;
		if (invitedIds != null ? !invitedIds.equals(event.invitedIds) : event.invitedIds != null)
			return false;
		if (attendingIds != null ? !attendingIds.equals(event.attendingIds) : event.attendingIds != null)
			return false;
		return !(eventText != null ? !eventText.equals(event.eventText) : event.eventText != null);

	}

	@Override
	public int hashCode() {
		int result = socialNetworkId;
		result = 31 * result + (contentTimestamp != null ? contentTimestamp.hashCode() : 0);
		result = 31 * result + (crawlingTimestamp != null ? crawlingTimestamp.hashCode() : 0);
		result = 31 * result + (int) (id ^ (id >>> 32));
		result = 31 * result + name.hashCode();
		result = 31 * result + (hostIds != null ? hostIds.hashCode() : 0);
		result = 31 * result + (int) (locationId ^ (locationId >>> 32));
		result = 31 * result + (invitedIds != null ? invitedIds.hashCode() : 0);
		result = 31 * result + (attendingIds != null ? attendingIds.hashCode() : 0);
		result = 31 * result + (eventText != null ? eventText.hashCode() : 0);
		return result;
	}
}
