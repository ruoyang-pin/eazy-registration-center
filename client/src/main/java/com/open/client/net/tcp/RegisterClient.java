package com.open.client.net.tcp;

import com.open.client.net.handler.RegisterClientInitializer;
import com.open.common.domain.InstanceInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

import static com.open.common.constant.CommonConstant.N_THREADS;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@Slf4j
@Service
public class RegisterClient {

    private Bootstrap serverBootstrap;

    private EventLoopGroup serverEventLoopGroup;

    private Channel channel;

    @SneakyThrows
    public ChannelFuture startServer(InetSocketAddress address) {
        log.info("register client starting...");

        serverEventLoopGroup = new NioEventLoopGroup(N_THREADS);

        serverBootstrap = new Bootstrap()
                .channel(NioServerSocketChannel.class)
                .handler(new RegisterClientInitializer())
                .group(serverEventLoopGroup);
        channel = serverBootstrap.connect(address).channel();
        return serverBootstrap.connect(address).sync();
    }

    public void pushMsg(InstanceInfo msg) {
        channel.writeAndFlush(msg);
    }

}
