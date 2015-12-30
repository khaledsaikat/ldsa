package de.due.ldsa.ld.model;

import java.util.Date;

public class HumanProfile extends Profile {
	private Sex sex;
	private Date birthdate;
	private Relationship relationship;

	public HumanProfile(ContentMeta metaInfos) {
		super(metaInfos);
	}

	public HumanProfile(ContentMeta metaInfos, Sex sex, Date birthdate, Relationship relationship) {
		super(metaInfos);
		this.sex = sex;
		this.birthdate = birthdate;
		this.relationship = relationship;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

}
