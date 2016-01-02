package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import de.due.ldsa.db.model.RelationshipStatus;

import java.nio.ByteBuffer;

/**
 *
 */
public class RelationshipStatusCodec extends TypeCodec<RelationshipStatus> {
    public RelationshipStatusCodec() {
        super(DataType.cint(), RelationshipStatus.class);
    }

    @Override
    public ByteBuffer serialize(RelationshipStatus value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return TypeCodec.cint().serialize(value.ordinal(), protocolVersion);
    }

    @Override
    public RelationshipStatus deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        int m = TypeCodec.cint().deserialize(bytes, protocolVersion);
        return RelationshipStatus.fromOrdinal(m);
    }

    @Override
    public RelationshipStatus parse(String value) throws InvalidTypeException {
        return RelationshipStatus.valueOf(value);
    }

    @Override
    public String format(RelationshipStatus value) throws InvalidTypeException {
        return value.toString();
    }
}
