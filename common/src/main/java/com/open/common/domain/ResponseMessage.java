package com.open.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    private int status;

    private String msg;

}
