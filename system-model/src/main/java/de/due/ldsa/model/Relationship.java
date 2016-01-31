package de.due.ldsa.model;

import java.util.ArrayList;

/**
 * Author: Romina (scrobart)
 *
 * This is not serializable. Serialize the HumanProfile instead.
 */
public class Relationship
{
    public Relationship(RelationshipStatus relationshipStatus, ArrayList<Long> personIds) {
        this.relationshipStatus = relationshipStatus;
        this.personIds = personIds;
    }

    RelationshipStatus relationshipStatus;
    ArrayList<Long> personIds;

    public RelationshipStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public ArrayList<Long> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(ArrayList<Long> personIds) {
        this.personIds = personIds;
    }
}
