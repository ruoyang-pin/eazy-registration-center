package com.open.client.net.handler;

import com.open.common.domain.InstanceInfo;
import com.open.common.util.KryoUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
public class InstanceInfoToByte extends MessageToByteEncoder<InstanceInfo> {


    @Override
    protected void encode(ChannelHandlerContext ctx, InstanceInfo msg, ByteBuf out) {
        byte[] bytes = KryoUtil.doSerialization(msg);
        out.writeBytes(bytes);
    }


}
