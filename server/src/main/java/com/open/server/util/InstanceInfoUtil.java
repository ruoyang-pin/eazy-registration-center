package com.open.server.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.open.common.domain.InstanceInfo;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
public class InstanceInfoUtil {

    //TODO 剔除端口号
    public static final Map<String, Map<InetSocketAddress, InstanceInfo>> INSTANCE_INFO_MAP = Maps.newConcurrentMap();

    /**
     * 添加或者更新实例
     *
     * @param instanceInfo 实例
     */
    public static void insertOrUpdateInstance(InstanceInfo instanceInfo) {
        INSTANCE_INFO_MAP.compute(instanceInfo.getServerName(), (k, v) -> {
            if (v != null) {
                v.compute(instanceInfo.getAddress(), (k1, v1) -> v1 == null ||
                        v1.getTimestamp() < instanceInfo.getTimestamp() ? instanceInfo : v1);
                return v;
            } else {
                Map<InetSocketAddress, InstanceInfo> map = Maps.newHashMap();
                map.put(instanceInfo.getAddress(), instanceInfo);
                return map;
            }
        });
    }

    public static Map<String, List<InstanceInfo>> getInstanceInfos() {
        Map<String, List<InstanceInfo>> result = Maps.newHashMap();
        INSTANCE_INFO_MAP
                .forEach((k, v) -> {
                            result.put(k, Lists.newArrayList(v.values()));
                        }
                );
        return result;
    }


}
