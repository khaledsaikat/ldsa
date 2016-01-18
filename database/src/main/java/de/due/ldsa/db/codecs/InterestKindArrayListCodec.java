package de.due.ldsa.db.codecs;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import com.google.common.reflect.TypeToken;
import de.due.ldsa.db.DbException;
import de.due.ldsa.db.model.InterestKind;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Romina (scrobart)
 *
 * Used to save an ArrayList<InterestKind> into Cassandra. (used in SocialNetworkInterest)
 */
public class InterestKindArrayListCodec extends TypeCodec<ArrayList<InterestKind>> {
    public InterestKindArrayListCodec() {
        super(DataType.list(DataType.cint()), (TypeToken<ArrayList<InterestKind>>) new TypeToken<ArrayList<InterestKind>>() {
        });
    }

    @Override
    public ByteBuffer serialize(ArrayList<InterestKind> value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        ArrayList<Integer> trueValue = new ArrayList<Integer>();
        if (value != null) {
            for (InterestKind ik : value) {
                trueValue.add(new Integer(ik.ordinal()));
            }
        }
        return TypeCodec.list(TypeCodec.cint()).serialize(trueValue, protocolVersion);
    }

    @Override
    public ArrayList<InterestKind> deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        List<Integer> temp = TypeCodec.list(TypeCodec.cint()).deserialize(bytes, protocolVersion);
        ArrayList<InterestKind> trueValue = new ArrayList<InterestKind>();

        for (Integer i : temp) {
            trueValue.add(InterestKind.fromOrdinal(i));
        }
        return trueValue;
    }

    @Override
    public ArrayList<InterestKind> parse(String value) throws InvalidTypeException {
        System.out.println("Parse ArrayList<InterestKind> not supported.");
        throw new DbException("not implemented.");
    }

    @Override
    public String format(ArrayList<InterestKind> value) throws InvalidTypeException {
        System.out.println("Format ArrayList<InterestKind> not supported.");
        throw new DbException("not implemented.");
    }
}
