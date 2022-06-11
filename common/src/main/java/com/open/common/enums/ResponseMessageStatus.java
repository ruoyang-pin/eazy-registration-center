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
public enum ResponseMessageStatus {

    SUCCESS(200, "success"),
    FAIL(500, "fail");

    private final int code;

    private final String name;

}
