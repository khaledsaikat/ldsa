package de.due.ldsa.db.codecs;


import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class OffsetDateTimeCodec extends TypeCodec<OffsetDateTime>
{
    public OffsetDateTimeCodec()
    {
        super(DataType.timestamp(),OffsetDateTime.class);
    }

    @Override
    public ByteBuffer serialize(OffsetDateTime value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        Instant temp1 = value.toInstant();
        Date temp2 = Date.from(temp1);
        return TypeCodec.timestamp().serialize(temp2,protocolVersion);
    }

    @Override
    public OffsetDateTime deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException
    {
        Date temp1 = TypeCodec.timestamp().deserialize(bytes,protocolVersion);
        Instant temp2 = temp1.toInstant();
        return OffsetDateTime.ofInstant(temp2, ZoneOffset.UTC);
    }

    @Override
    public OffsetDateTime parse(String value) throws InvalidTypeException {
        System.out.println("Parsing " + value);
        return OffsetDateTime.parse(value);
    }

    @Override
    public String format(OffsetDateTime value) throws InvalidTypeException {
        System.out.println("Formatting...");
        return value.toString();
    }


}