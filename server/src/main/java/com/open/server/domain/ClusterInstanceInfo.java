package com.open.server.domain;

import lombok.Data;

import java.net.InetAddress;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@Data
public class ClusterInstanceInfo {

    private String ClusterName;

    private InetAddress address;

    private int healthCode;


}
