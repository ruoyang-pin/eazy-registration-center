package com.open.server.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.open.common.domain.InstanceInfo;

import java.util.Map;
import java.util.Set;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
public class InstanceInfoUtil {

    public static final Map<String, Set<InstanceInfo>> INSTANCE_INFO_MAP = Maps.newConcurrentMap();


    /**
     * 添加或者更新实例
     *
     * @param instanceInfo
     */
    public static void insertOrUpdateInstance(InstanceInfo instanceInfo) {
        INSTANCE_INFO_MAP.compute(instanceInfo.getServerName(), (k, v) -> {
            if (v != null) {
                v.forEach(f -> {
                    if (f.equals(instanceInfo) && f.getTimestamp() < instanceInfo.getTimestamp()) {
                        f.copyValue(instanceInfo);
                    }
                });
                return v;
            } else {
                Set<InstanceInfo> hashSet = Sets.newConcurrentHashSet();
                hashSet.add(instanceInfo);
                return hashSet;
            }
        });
    }


}
