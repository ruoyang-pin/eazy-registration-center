package com.open.client.net.handler;

import com.open.common.domain.InstanceInfo;
import com.open.common.domain.ResponseMessage;
import com.open.common.util.KryoUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

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
//        ctx.pipeline().close();
//        KryoUtil.remove();
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
