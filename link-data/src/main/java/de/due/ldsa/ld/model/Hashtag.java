package de.due.ldsa.ld.model;

import java.util.List;

public class Hashtag {
	private String title;
	private List<SocialNetworkContent> usedAt;

	public Hashtag() {
		super();
	}

	public Hashtag(String title, List<SocialNetworkContent> usedAt) {
		super();
		this.title = title;
		this.usedAt = usedAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SocialNetworkContent> getUsedAt() {
		return usedAt;
	}

	public void setUsedAt(List<SocialNetworkContent> usedAt) {
		this.usedAt = usedAt;
	}
}
