package de.due.ldsa.ld.model;

public class Location extends SocialNetworkContent {
	private String name;
	private int timesUsed;
	private String city;
	private String country;
	private Position position;

	public Location(ContentMeta metaInfos) {
		super(metaInfos);
	}

	public Location(ContentMeta metaInfos, String name, int timesUsed, String city, String country, Position position) {
		super(metaInfos);
		this.name = name;
		this.timesUsed = timesUsed;
		this.city = city;
		this.country = country;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimesUsed() {
		return timesUsed;
	}

	public void setTimesUsed(int timesUsed) {
		this.timesUsed = timesUsed;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
