package de.due.ldsa.ld.model;

public class SocialNetwork {
	private String name;
	private String url;
	private byte[] logo;

	public SocialNetwork() {
		super();
	}

	public SocialNetwork(String name, String url, byte[] logo) {
		super();
		this.name = name;
		this.url = url;
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

}
