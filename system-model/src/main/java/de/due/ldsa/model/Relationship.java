package de.due.ldsa.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;

/**
 *
 */
public class Relationship implements Serializable {
	RelationshipStatus relationshipStatus;
	ArrayList<HumanProfile> persons;

	public RelationshipStatus getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public ArrayList<HumanProfile> getPersons() {
		return persons;
	}

	public void setPersons(ArrayList<HumanProfile> persons) {
		this.persons = persons;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Relationship))
			return false;

		Relationship that = (Relationship) o;

		if (relationshipStatus != that.relationshipStatus)
			return false;
		return persons.equals(that.persons);

	}

	@Override
	public int hashCode() {
		int result = relationshipStatus.hashCode();
		result = 31 * result + persons.hashCode();
		return result;
	}

	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
