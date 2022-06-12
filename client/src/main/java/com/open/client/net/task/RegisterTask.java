package com.open.client.net.task;

import com.open.client.net.config.RegisterClientConfig;
import com.open.client.net.tcp.RegisterClient;
import com.open.common.domain.InstanceInfo;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@Slf4j
@Component
@EnableScheduling
public class RegisterTask {

    @Resource
    private RegisterClient registerClient;

    @Resource
    private RegisterClientConfig clientConfig;


    @Scheduled(cron = "*/10 * * * * ?")
    public void submitInfo() {
        InetSocketAddress address = clientConfig.getServerAddress();
        ChannelFuture channelFuture = registerClient.startServer(address);
        channelFuture.addListener(f -> {
            if (f.isSuccess()) {
                log.info("已连接上远端server:{}", address);
                //注册服务
                InstanceInfo instanceInfo = InstanceInfo.builder()
                        .timestamp(System.currentTimeMillis())
                        .serverName(clientConfig.getClientName())
                        .build();
                registerClient.pushMsg(instanceInfo);
            } else {
                log.info("连接远端server:{}失败", address);
            }
        });
    }
}
