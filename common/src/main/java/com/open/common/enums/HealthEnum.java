package com.open.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@AllArgsConstructor
@Getter
public enum HealthEnum {

    INVALID(-1, "无效"),

    SUB_HEALTH(0, "亚健康"),

    HEALTHY(2, "有效");

    private final int code;

    private final String name;
}
