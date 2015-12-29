package de.due.ldsa.db;

/**
 *
 */
public class DbException extends RuntimeException
{

    public DbException(String reason)
    {
        super(reason);
    }
}
