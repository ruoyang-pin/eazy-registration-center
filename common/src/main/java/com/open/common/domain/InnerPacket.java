package com.open.common.domain;

import lombok.Data;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

/**
 * @author rich
 * @date
 * @description: 集群内部通讯包
 */
@Data
public class InnerPacket {

    /**
     * k->服务名 v->实例信息
     */
    private Map<String, List<InstanceInfo>> instanceInfoMap;

    /**
     * 下次传播排除的实例节点
     */
    private List<String> excludeClusterNames;

    @Data
    public static class InstanceInfo {

        /**
         * 实例版本
         */
        private int version;

        /**
         * ip地址
         */
        private InetAddress address;

        /**
         * 健康状态
         */
        private int healthCode;

    }

}
