package de.due.ldsa.ld.model;

import java.util.Date;

public class CoopProfile extends Profile {
	private Date foundedDate;
	private Location location;

	public CoopProfile(ContentMeta metaInfos) {
		super(metaInfos);
	}

	public CoopProfile(ContentMeta metaInfos, Date foundedDate, Location location) {
		super(metaInfos);
		this.foundedDate = foundedDate;
		this.location = location;
	}

	public Date getFoundedDate() {
		return foundedDate;
	}

	public void setFoundedDate(Date foundedDate) {
		this.foundedDate = foundedDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
