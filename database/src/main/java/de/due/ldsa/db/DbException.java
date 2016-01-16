package de.due.ldsa.db;

/**
 * Author: Romina (scrobart)
 *
 */
public class DbException extends RuntimeException
{

    public DbException(String reason)
    {
        super(reason);
    }
}
