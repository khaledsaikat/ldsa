package de.due.ldsa.db;

/**
 * Author: Romina (scrobart)
 * Exception for Database errors.
 */
public class DbException extends RuntimeException
{

    public DbException(String reason)
    {
        super(reason);
    }
}
