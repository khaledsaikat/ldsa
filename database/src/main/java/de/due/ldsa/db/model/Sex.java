package de.due.ldsa.db.model;

/**
 *
 */
public enum Sex
{
    FEMALE,
    MALE,
    UNKNOWN;

    private static Sex[] allValues = values();

    public static Sex fromOrdinal(int n) {
        return allValues[n];
    }
}
