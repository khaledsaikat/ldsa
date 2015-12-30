package de.due.ldsa.ld.model;

import java.util.List;

public class Relationship {
	private RelationshipStatus stats;
	private List<HumanProfile> persons;

	public Relationship() {
		super();
	}

	public Relationship(RelationshipStatus stats, List<HumanProfile> persons) {
		super();
		this.stats = stats;
		this.persons = persons;
	}

	public RelationshipStatus getStats() {
		return stats;
	}

	public void setStats(RelationshipStatus stats) {
		this.stats = stats;
	}

	public List<HumanProfile> getPersons() {
		return persons;
	}

	public void setPersons(List<HumanProfile> persons) {
		this.persons = persons;
	}

}
