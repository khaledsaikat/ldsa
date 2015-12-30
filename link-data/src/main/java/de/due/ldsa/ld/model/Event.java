package de.due.ldsa.ld.model;

import java.util.List;

public class Event extends SocialNetworkContent {
	private String name;
	private List<Profile> hosts;
	private Location location;
	private List<Profile> invited;
	private List<Profile> attending;
	private String text;

	public Event(ContentMeta metaInfos) {
		super(metaInfos);
	}

	public Event(ContentMeta metaInfos, String name, List<Profile> hosts, Location location, List<Profile> invited,
			List<Profile> attending, String text) {
		super(metaInfos);
		this.name = name;
		this.hosts = hosts;
		this.location = location;
		this.invited = invited;
		this.attending = attending;
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Profile> getHosts() {
		return hosts;
	}

	public void setHosts(List<Profile> hosts) {
		this.hosts = hosts;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Profile> getInvited() {
		return invited;
	}

	public void setInvited(List<Profile> invited) {
		this.invited = invited;
	}

	public List<Profile> getAttending() {
		return attending;
	}

	public void setAttending(List<Profile> attending) {
		this.attending = attending;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
