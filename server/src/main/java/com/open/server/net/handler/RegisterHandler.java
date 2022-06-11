package com.open.server.net.handler;

import com.open.common.domain.InstanceInfo;
import com.open.common.domain.ResponseMessage;
import com.open.common.enums.ResponseMessageStatus;
import com.open.server.util.InstanceInfoUtil;
import com.open.common.util.KryoUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@Slf4j
public class RegisterHandler extends SimpleChannelInboundHandler<InstanceInfo> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, InstanceInfo msg) {
        InstanceInfoUtil.insertOrUpdateInstance(msg);
        //返回成功消息体
        ResponseMessage message = ResponseMessage.builder()
                .status(ResponseMessageStatus.SUCCESS.getCode())
                .msg(ResponseMessageStatus.SUCCESS.getName())
                .build();
        byte[] bytes = KryoUtil.doSerialization(message);
        ctx.writeAndFlush(bytes);
        //todo 关闭连接;
        KryoUtil.remove();
        log.info("客户端:{}注册成功", msg.getAddress());
    }
}
