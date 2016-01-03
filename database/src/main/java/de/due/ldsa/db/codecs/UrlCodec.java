package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;

/**
 *
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
            URL result = new URL(temp);
            return result;
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL saved in Database: " + temp.toString());
            return null;
        }
    }

    @Override
    public URL parse(String value) throws InvalidTypeException {
        try {
            URL result = new URL(value);
            return result;
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
