package com.open.server.net.handler;

import com.open.common.domain.InstanceInfo;
import com.open.common.domain.ResponseMessage;
import com.open.common.enums.ResponseMessageStatus;
import com.open.server.util.InstanceInfoUtil;
import com.open.common.util.KryoUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

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
        ctx.pipeline().writeAndFlush(Unpooled.wrappedBuffer(bytes));
        //todo 关闭连接;
//        KryoUtil.remove();
        log.info("客户端:{}注册成功", msg.getAddress());
    }

    @Override
    public void channelInactive(@NotNull ChannelHandlerContext ctx) {
        channelClose(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("socket exception:", cause);
        channelClose(ctx);
    }

    private void channelClose(ChannelHandlerContext ctx) {
        try {
            ChannelPipeline pipeline = ctx.pipeline();
            if (pipeline != null) {
                pipeline.close();
            }
        } catch (Exception e) {
            log.error("close channel error" + e);
        }
    }
}
