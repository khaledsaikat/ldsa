package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import com.google.common.reflect.TypeToken;
import de.due.ldsa.db.DbException;

import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Romina (scrobart)
 *
 * Used to save an ArrayList<Long> into Cassandra. (used to store various IDs)
 * Usually, you won't need to do anything with this class. All of this will be used by the Cassandra mapper internally.
 */
public class LongArrayListCodec extends TypeCodec<ArrayList<Long>> {
    public LongArrayListCodec() {
        super(DataType.list(DataType.bigint()), new TypeToken<ArrayList<Long>>() {
        });
    }

    @Override
    public ByteBuffer serialize(ArrayList<Long> value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return TypeCodec.list(TypeCodec.bigint()).serialize(value, protocolVersion);
    }

    @Override
    public ArrayList<Long> deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        List<Long> temp = TypeCodec.list(TypeCodec.bigint()).deserialize(bytes, protocolVersion);

        ArrayList<Long> result = new ArrayList<>();
        result.addAll(temp);
        return result;
    }

    @Override
    public ArrayList<Long> parse(String value) throws InvalidTypeException {
        System.out.println("Parse ArrayList<Long> not supported.");
        throw new DbException("not implemented.");
    }

    @Override
    public String format(ArrayList<Long> value) throws InvalidTypeException {
        System.out.println("Format ArrayList<Long> not supported.");
        throw new DbException("not implemented.");
    }
}
