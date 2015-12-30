package de.due.ldsa.ld.model;

public class Media extends SocialNetworkContent {
	private long size;
	private String crawlingPath;
	private String filename;
	private byte[] bytes;

	public Media(ContentMeta metaInfos) {
		super(metaInfos);
	}

	public Media(ContentMeta metaInfos, long size, String crawlingPath, String filename, byte[] bytes) {
		super(metaInfos);
		this.size = size;
		this.crawlingPath = crawlingPath;
		this.filename = filename;
		this.bytes = bytes;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getCrawlingPath() {
		return crawlingPath;
	}

	public void setCrawlingPath(String crawlingPath) {
		this.crawlingPath = crawlingPath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
