package com.open.common.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.open.common.domain.InstanceInfo;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
public class KryoUtil {

    static private final ThreadLocal<Kryo> KRYO_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(InstanceInfo.class);
        kryo.register(InetSocketAddress.class);
        kryo.register(int.class);
        kryo.register(String.class);
        kryo.register(long.class);
        return kryo;
    });


    public static void remove() {
        KRYO_THREAD_LOCAL.remove();
    }


    @SneakyThrows
    public static <T> byte[] doSerialization(T instanceInfo) {
        Kryo kryo = KRYO_THREAD_LOCAL.get();
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Output output = new Output(byteArrayOutputStream)
        ) {
            kryo.writeClassAndObject(output, instanceInfo);
            return byteArrayOutputStream.toByteArray();
        }
    }

    @SneakyThrows
    public static Object doDeserialize(byte[] bytes) {
        Kryo kryo = KRYO_THREAD_LOCAL.get();
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(inputStream)
        ) {
            return kryo.readClassAndObject(input);
        }
    }


}
