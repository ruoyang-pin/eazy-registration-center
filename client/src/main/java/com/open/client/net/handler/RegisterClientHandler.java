package com.open.client.net.handler;

import com.open.common.domain.InstanceInfo;
import com.open.common.domain.ResponseMessage;
import com.open.common.util.KryoUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import static com.open.common.enums.ResponseMessageStatus.SUCCESS;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
@Slf4j
public class RegisterClientHandler extends SimpleChannelInboundHandler<ResponseMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseMessage msg) {
        if (SUCCESS.getCode() == msg.getStatus()) {
            log.info("注册到远端服务:{}成功", ctx.pipeline().channel().remoteAddress());
        } else {
            log.warn("注册到远端服务:{}失败", ctx.pipeline().channel().remoteAddress());
        }
        //关闭连接
        ctx.pipeline().close();
        KryoUtil.remove();
    }

}
