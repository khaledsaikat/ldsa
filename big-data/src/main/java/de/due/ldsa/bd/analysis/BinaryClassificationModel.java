package de.due.ldsa.bd.analysis;

import java.io.Serializable;

/**
 * JavaBeans model for binary classification. This model is useful for logistic
 * regression.
 * 
 * @author Khaled Hossain
 */
public class BinaryClassificationModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Double label;
	private String text;

	public BinaryClassificationModel() {
	}

	public BinaryClassificationModel(Double label, String text) {
		this.setLabel(label);
		this.setText(text);
	}

	public Double getLabel() {
		return label;
	}

	public void setLabel(Double label) {
		this.label = label;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
