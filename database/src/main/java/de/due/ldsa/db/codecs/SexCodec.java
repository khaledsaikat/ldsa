package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import de.due.ldsa.db.model.RelationshipStatus;
import de.due.ldsa.db.model.Sex;

import java.nio.ByteBuffer;

/**
 *
 */
public class SexCodec extends TypeCodec<Sex> {
    public SexCodec() {
        super(DataType.cint(), Sex.class);
    }

    @Override
    public ByteBuffer serialize(Sex value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return TypeCodec.cint().serialize(value.ordinal(), protocolVersion);
    }

    @Override
    public Sex deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        int m = TypeCodec.cint().deserialize(bytes, protocolVersion);
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
