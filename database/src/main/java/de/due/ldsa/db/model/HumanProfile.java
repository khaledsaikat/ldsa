package de.due.ldsa.db.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 */
public class HumanProfile extends Profile
{
    Sex sex;
    LocalDate birthday;
    Relationship relationship;

    public int getAge()
    {
        Date temp = new Date();
        LocalDate today = temp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int)ChronoUnit.YEARS.between(today,birthday);
    }

    public void setRelationship(Relationship relationship)
    {
        this.relationship = relationship;
    }
}
