package com.open.common.domain;

import lombok.*;

import java.net.InetSocketAddress;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InstanceInfo {

    /**
     * 实例版本
     */
    private long timestamp;
    /**
     * 实例名称
     */
    private String serverName;
    /**
     * ip地址
     */
    @EqualsAndHashCode.Include
    private InetSocketAddress address;
    /**
     * 健康状态
     */
    private int healthCode;





}
