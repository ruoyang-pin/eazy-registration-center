package com.open.client.net.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@ConfigurationProperties("register.config")
@Data
public class RegisterClientConfig {

    @Value("${server.host}")
    private String host;

    @Value("${server.port}")
    private int port;

    @Value("${client.name}")
    private String clientName;

    private InetSocketAddress serverAddress;

    @PostConstruct
    private void init() {
        serverAddress = new InetSocketAddress(host, port);
    }


}
