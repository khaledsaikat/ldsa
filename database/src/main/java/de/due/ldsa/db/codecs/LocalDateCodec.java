package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import de.due.ldsa.db.DbException;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 */
public class LocalDateCodec extends TypeCodec<LocalDate> {
    public LocalDateCodec() {
        super(DataType.timestamp(), LocalDate.class);
    }

    @Override
    public ByteBuffer serialize(LocalDate value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        if (value == null)
        {
            return TypeCodec.timestamp().serialize(null,protocolVersion);
        }
        Date temp = Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return TypeCodec.timestamp().serialize(temp, protocolVersion);
    }

    @Override
    public LocalDate deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        Date temp = TypeCodec.timestamp().deserialize(bytes, protocolVersion);
        if (temp == null) return null;
        LocalDate temp2 = temp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return temp2;
    }

    @Override
    public LocalDate parse(String value) throws InvalidTypeException {
        throw new DbException("LocalDateCodec parse not implemented.");
    }

    @Override
    public String format(LocalDate value) throws InvalidTypeException {
        throw new DbException("LocalDateCodec format not implemented.");
    }
}
