package de.due.ldsa.ld.model;

import java.util.Date;

public class ContentMeta {
	private Date contentTimestamp;
	private Date crawlingTimestamp;
	private SocialNetwork sourceNetwork;

	public ContentMeta() {
		super();
	}

	public ContentMeta(Date contentTimestamp, Date crawlingTimestamp, SocialNetwork sourceNetwork) {
		super();
		this.contentTimestamp = contentTimestamp;
		this.crawlingTimestamp = crawlingTimestamp;
		this.sourceNetwork = sourceNetwork;
	}

	public Date getContentTimestamp() {
		return contentTimestamp;
	}

	public void setContentTimestamp(Date contentTimestamp) {
		this.contentTimestamp = contentTimestamp;
	}

	public Date getCrawlingTimestamp() {
		return crawlingTimestamp;
	}

	public void setCrawlingTimestamp(Date crawlingTimestamp) {
		this.crawlingTimestamp = crawlingTimestamp;
	}

	public SocialNetwork getSourceNetwork() {
		return sourceNetwork;
	}

	public void setSourceNetwork(SocialNetwork sourceNetwork) {
		this.sourceNetwork = sourceNetwork;
	}
}
