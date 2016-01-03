package de.due.ldsa.db.model;

/**
 *
 */
public enum InterestKind
{
    PRODUCT,
    COMPANY,
    NONPROFIT,
    SPORT,
    MUSIC,
    FILM,
    BOOK,
    FOOD,
    NON_ALC_DRINK,
    ALC_DRINK,
    PLACE,
    RESTAURANT,
    BAR,
    HOLIDAYS,
    SHOP,
    EVENT,
    FASHION,
    PERSON,
    POLITICS,
    RELIGION,
    EDUCATION,
    UNKNOWN;

    private static InterestKind[] allValues = values();
    public static InterestKind fromOrdinal(int n)
    {
        return allValues[n];
    }
}
