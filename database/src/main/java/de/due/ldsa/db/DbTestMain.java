package de.due.ldsa.db;

/**
 *
 */
public class DbTestMain
{
    public static void main(String[] args)
    {
        Database db = new DatabaseImpl();
        System.out.println(db.sayHello());
    }
}
