package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import com.google.common.reflect.TypeToken;
import de.due.ldsa.db.DbException;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StringArrayListCodec extends TypeCodec<ArrayList<String>> {
    public StringArrayListCodec() {
        super(DataType.list(DataType.varchar()), (TypeToken<ArrayList<String>>) new TypeToken<ArrayList<String>>() {
        });
    }

    @Override
    public ByteBuffer serialize(ArrayList<String> value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return TypeCodec.list(TypeCodec.varchar()).serialize(value, protocolVersion);
    }

    @Override
    public ArrayList<String> deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        List<String> temp = TypeCodec.list(TypeCodec.varchar()).deserialize(bytes, protocolVersion);

        ArrayList<String> result = new ArrayList<String>();
        result.addAll(temp);
        return result;
    }

    @Override
    public ArrayList<String> parse(String value) throws InvalidTypeException {
        System.out.println("Parse ArrayList<String> not supported.");
        throw new DbException("not implemented.");
    }

    @Override
    public String format(ArrayList<String> value) throws InvalidTypeException {
        System.out.println("Format ArrayList<String> not supported.");
        throw new DbException("not implemented.");
    }
}
