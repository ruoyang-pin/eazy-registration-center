package com.open.common.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.net.InetSocketAddress;

/**
 * @author rich
 * @date 2022/7/9
 * @description
 */
public class InetSocketAddressSerializer extends Serializer {


    @Override
    public void write(Kryo kryo, Output output, Object object) {
        if (!(object instanceof InetSocketAddress)) {
            throw new IllegalArgumentException(String.format("only accept InetSocketAddress but give a %s", object.getClass()));
        }
        InetSocketAddress address = (InetSocketAddress) object;
        output.writeString(address.getHostString());
        output.writeInt(address.getPort());
    }

    @Override
    public Object read(Kryo kryo, Input input, Class type) {
        String host = input.readString();
        int port = input.readInt();
        return new InetSocketAddress(host, port);
    }
}
