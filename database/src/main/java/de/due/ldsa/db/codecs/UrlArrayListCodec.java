package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import com.google.common.reflect.TypeToken;
import de.due.ldsa.db.DbException;
import de.due.ldsa.db.model.InterestKind;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Romina (scrobart)
 *
 * Used to save an ArrayList<URL> into Cassandra. (used in ProfileFeed)
 */
public class UrlArrayListCodec extends TypeCodec<ArrayList<URL>> {
    public UrlArrayListCodec() {
        super(DataType.list(DataType.varchar()), (TypeToken<ArrayList<URL>>) new TypeToken<ArrayList<URL>>() {
        });
    }

    @Override
    public ByteBuffer serialize(ArrayList<URL> value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        ArrayList<String> trueValue = new ArrayList<String>();
        if (value != null) {
            for (URL url : value) {
                trueValue.add(url.toString());
            }
        }
        return TypeCodec.list(TypeCodec.varchar()).serialize(trueValue, protocolVersion);
    }

    @Override
    public ArrayList<URL> deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        List<String> temp = TypeCodec.list(TypeCodec.varchar()).deserialize(bytes, protocolVersion);
        ArrayList<URL> trueValue = new ArrayList<URL>();

        for (String i : temp) {
            try {
                trueValue.add(new URL(i));
            } catch (MalformedURLException e) {
                System.out.println("Could not load URL :" + i);
            }
        }
        return trueValue;
    }

    @Override
    public ArrayList<URL> parse(String value) throws InvalidTypeException {
        System.out.println("Parse ArrayList<URL> not supported.");
        throw new DbException("not implemented.");
    }

    @Override
    public String format(ArrayList<URL> value) throws InvalidTypeException {
        System.out.println("Format ArrayList<URL> not supported.");
        throw new DbException("not implemented.");
    }
}
