package com.open.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.ref.PhantomReference;
import java.util.List;
import java.util.Map;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage implements Serializable {

    private int status;

    private String msg;

    private Map<String, List<InstanceInfo>> instanceInfos;

}
