package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import de.due.ldsa.model.RelationshipStatus;
import de.due.ldsa.model.Sex;

import java.nio.ByteBuffer;

/**
 * Author: Romina (scrobart)
 *
 * Used to save an Sex into Cassandra. (used in HumanProfile)
 */
public class SexCodec extends TypeCodec<Sex> {
    public SexCodec() {
        super(DataType.cint(), Sex.class);
    }

    @Override
    public ByteBuffer serialize(Sex value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        if ((value != Sex.FEMALE) && (value != Sex.MALE)) {
            return TypeCodec.cint().serialize(2, protocolVersion);
        }
        return TypeCodec.cint().serialize(value.ordinal(), protocolVersion);
    }

    @Override
    public Sex deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        int m = TypeCodec.cint().deserialize(bytes, protocolVersion);
        if ((m != 0) && (m != 1)) {
            System.out.println("The sex codec returned: " + new Integer(m).toString());
            return null;
        }
        return Sex.fromOrdinal(m);
    }

    @Override
    public Sex parse(String value) throws InvalidTypeException {
        return Sex.valueOf(value);
    }

    @Override
    public String format(Sex value) throws InvalidTypeException {
        return value.toString();
    }
}
