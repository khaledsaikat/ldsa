package de.due.ldsa.db.model;

import java.io.Serializable;

/**
 * Author: Romina (scrobart)
 *
 */
public enum RelationshipStatus implements Serializable
{
    SINGLE,
    TAKEN,
    ENGAGED,
    MARRIED,
    CIVILUNION,
    DOMESTIC_PARTNER,
    OPEN_RELATIONSHIP,
    COMPILICATED,
    SEPARATED,
    DIVORCED,
    WIDOWED,
    UNKNOWN;


    private static RelationshipStatus[] allValues = values();

    public static RelationshipStatus fromOrdinal(int n) {
        return allValues[n];
    }
}
