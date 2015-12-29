package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import de.due.ldsa.db.DbException;

import java.nio.ByteBuffer;

/**
 *
 */
public class ByteArrayCodec extends TypeCodec<byte[]> {
    public ByteArrayCodec() {
        super(DataType.blob(), byte[].class);
    }

    @Override
    public String format(byte[] value) throws InvalidTypeException {
        System.out.println("Format OffsetDateTimeCodec not supported.");
        throw new DbException("not implemented.");
    }

    @Override
    public byte[] parse(String value) throws InvalidTypeException {
        System.out.println("Parse Byte-Array not supported.");
        throw new DbException("not implemented.");
    }

    @Override
    public byte[] deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        ByteBuffer byteBuffer = TypeCodec.blob().deserialize(bytes, protocolVersion);
        return byteBuffer.array();
    }

    @Override
    public ByteBuffer serialize(byte[] value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        ByteBuffer temp = ByteBuffer.wrap(value);
        return TypeCodec.blob().serialize(temp, protocolVersion);
    }
}
