package com.open.client.net.tcp;

import com.open.client.net.handler.RegisterClientInitializer;
import com.open.common.domain.InstanceInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

import static com.open.common.constant.CommonConstant.N_THREADS;
import static io.netty.channel.ChannelOption.SO_KEEPALIVE;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@Slf4j
@Service
public class RegisterClient {

    private Channel channel;

    @SneakyThrows
    public ChannelFuture startServer(InetSocketAddress address) {
        if (channel != null && channel.isActive()) {
            return channel.newSucceededFuture();
        }

        log.info("register client starting...");
        EventLoopGroup serverEventLoopGroup = new NioEventLoopGroup(N_THREADS);

        Bootstrap serverBootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .handler(new RegisterClientInitializer())
                .option(SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .group(serverEventLoopGroup);
        ChannelFuture future = serverBootstrap.connect(address).sync();
        channel = future.channel();
        return future;
    }

    public void pushMsg(InstanceInfo msg) {
        channel.writeAndFlush(msg);
    }

}
