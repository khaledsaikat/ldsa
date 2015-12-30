package de.due.ldsa.ld.model;

public class SocialNetworkContent {
	private ContentMeta metaInfos;

	public SocialNetworkContent(ContentMeta metaInfos) {
		super();
		this.metaInfos = metaInfos;
	}

	public SocialNetworkContent() {
		super();
	}

	public ContentMeta getMetaInfos() {
		return metaInfos;
	}

	public void setMetaInfos(ContentMeta metaInfos) {
		this.metaInfos = metaInfos;
	}
}
