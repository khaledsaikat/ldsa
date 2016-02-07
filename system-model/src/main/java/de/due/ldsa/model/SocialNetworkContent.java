package de.due.ldsa.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import de.due.ldsa.exception.ModelException;

/**
 * Author: Romina (scrobart)
 *
 */
public interface SocialNetworkContent extends Serializable {
	// We need to put these methods here, because Datastax' Mapping driver does
	// not support inheritance.
	// If we would declare fields in an abstract class, they would neither be
	// written nor read in the database.

	OffsetDateTime getContentTimestamp() throws ModelException;

	OffsetDateTime getCrawlingTimestamp() throws ModelException;

	void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws ModelException;

	int getSocialNetworkId();

	ContentMeta getContentMeta();

	/**
	 * Sets the ID this object will have in the database.
	 *
	 * @param id The ID this object has in the database.
	 */
	void setId(long id);

	/**
	 * Returns the ID this object has in the Database.
	 * @return The ID this object has in the Database.
	 */
	long getId();

	void changeTimezones(ZoneOffset zo);
}
