package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;

/**
 * Author: Romina (scrobart)
 *
 * Used to save an URL into Cassandra. (used in Profile)
 * Usually, you won't need to do anything with this class. All of this will be used by the Cassandra mapper internally.
 */
public class UrlCodec extends TypeCodec<URL> {
    public UrlCodec() {
        super(DataType.varchar(), URL.class);
    }

    @Override
    public ByteBuffer serialize(URL value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        if (value == null)
        {
            return TypeCodec.varchar().serialize("",protocolVersion);
        }
        return TypeCodec.varchar().serialize(value.toString(), protocolVersion);
    }

    @Override
    public URL deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        String temp = TypeCodec.varchar().deserialize(bytes, protocolVersion);
        try {
            return new URL(temp);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL saved in Database: " + temp);
            return null;
        }
    }

    @Override
    public URL parse(String value) throws InvalidTypeException {
        try {
            return new URL(value);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL saved in Database: " + value);
            return null;
        }
    }

    @Override
    public String format(URL value) throws InvalidTypeException {
        return value.toString();
    }
}
