package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import de.due.ldsa.model.InterestKind;
import de.due.ldsa.model.Sex;

import java.nio.ByteBuffer;

/**
 * Author: Romina (scrobart)
 *
 * Used to save an InterestKind into Cassandra. (required for SocialNetworkInterest)
 */
public class InterestKindCodec extends TypeCodec<InterestKind> {
    public InterestKindCodec() {
        super(DataType.cint(), InterestKind.class);
    }

    @Override
    public ByteBuffer serialize(InterestKind value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return TypeCodec.cint().serialize(value.ordinal(), protocolVersion);
    }

    @Override
    public InterestKind deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        int m = TypeCodec.cint().deserialize(bytes, protocolVersion);
        return InterestKind.fromOrdinal(m);
    }

    @Override
    public InterestKind parse(String value) throws InvalidTypeException {
        return InterestKind.valueOf(value);
    }

    @Override
    public String format(InterestKind value) throws InvalidTypeException {
        return value.toString();
    }
}
