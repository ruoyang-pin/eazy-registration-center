package com.open.server.net.tcp;

import com.open.server.net.handler.RegisterServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static com.open.common.constant.CommonConstant.N_THREADS;
import static com.open.common.constant.CommonConstant.REGISTER_SERVER_PORT;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@Slf4j
public class RegisterServer {

    private ServerBootstrap serverBootstrap;

    private EventLoopGroup serverEventLoopGroup;



    @SneakyThrows
    public void startServer() {
        log.info("register Server starting...");
        serverEventLoopGroup = new NioEventLoopGroup(N_THREADS);
        serverBootstrap = new ServerBootstrap()
                .channel(NioServerSocketChannel.class)
                .childHandler(new RegisterServerInitializer())
                .group(serverEventLoopGroup);
        serverBootstrap.bind(REGISTER_SERVER_PORT).syncUninterruptibly();
    }

    public static void main(String[] args) {
        new RegisterServer().startServer();
    }


}
