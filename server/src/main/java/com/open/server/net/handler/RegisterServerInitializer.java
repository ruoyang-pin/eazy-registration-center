package com.open.server.net.handler;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


public class RegisterServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(
                new ErcConvert(),
                new RegisterHandler()
        );
    }
}
