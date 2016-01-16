package de.due.ldsa.db.model;

import java.io.Serializable;

/**
 * Author: Romina (scrobart)
 *
 */
public enum Sex implements Serializable
{
    FEMALE,
    MALE,
    UNKNOWN;

    private static Sex[] allValues = values();

    public static Sex fromOrdinal(int n) {
        return allValues[n];
    }
}
