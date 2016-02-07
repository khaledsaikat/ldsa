package de.due.ldsa.db.codecs;


import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import de.due.ldsa.db.DbException;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Author: Romina (scrobart)
 * <p>
 * Used to save an OffsetDateTime into Cassandra. (used in SocialNetworkContent)
 * Usually, you won't need to do anything with this class. All of this will be used by the Cassandra mapper internally.
 */
public class OffsetDateTimeCodec extends TypeCodec<OffsetDateTime>
{
    public OffsetDateTimeCodec()
    {
        super(DataType.timestamp(),OffsetDateTime.class);
    }

    @Override
    public ByteBuffer serialize(OffsetDateTime value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        if (value == null) {
            return TypeCodec.timestamp().serialize(null, protocolVersion);
        }
        Instant temp1 = value.toInstant();
        Date temp2 = Date.from(temp1);
        return TypeCodec.timestamp().serialize(temp2,protocolVersion);
    }

    @Override
    public OffsetDateTime deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException
    {
        Date temp1 = TypeCodec.timestamp().deserialize(bytes,protocolVersion);
        if (temp1 == null) return null;
        Instant temp2 = temp1.toInstant();
        return OffsetDateTime.ofInstant(temp2, ZoneOffset.UTC);
    }

    @Override
    public OffsetDateTime parse(String value) throws InvalidTypeException {
        /*System.out.println("Parsing " + value);
        return OffsetDateTime.parse(value);*/
        System.out.println("Parse OffsetDateTimeCodec not supported.");
        throw new DbException("not implemented.");
    }

    @Override
    public String format(OffsetDateTime value) throws InvalidTypeException {
        /*System.out.println("Formatting...");
        return value.toString();*/
        System.out.println("Format OffsetDateTimeCodec not supported.");
        throw new DbException("not implemented.");
    }


}