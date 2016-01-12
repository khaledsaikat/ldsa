package de.due.ldsa.bd.analysis;

import java.io.Serializable;

public class CommentSample implements Serializable {
	private static final long serialVersionUID = 1L;
	private String text;
	
	public CommentSample(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
