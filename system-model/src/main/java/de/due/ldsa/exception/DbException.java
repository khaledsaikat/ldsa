package de.due.ldsa.exception;

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
