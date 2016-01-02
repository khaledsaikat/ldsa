package de.due.ldsa.db.model;

import java.util.ArrayList;

/**
 *
 */
public class Relationship
{
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
        if (this == o) return true;
        if (!(o instanceof Relationship)) return false;

        Relationship that = (Relationship) o;

        if (relationshipStatus != that.relationshipStatus) return false;
        return persons.equals(that.persons);

    }

    @Override
    public int hashCode() {
        int result = relationshipStatus.hashCode();
        result = 31 * result + persons.hashCode();
        return result;
    }
}
