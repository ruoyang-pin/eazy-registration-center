package com.open.client.net.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
public class RegisterClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(
                new ByteToResponseMessage(),
                new RegisterClientHandler(),
                new InstanceInfoToByte()
        );
    }
}
