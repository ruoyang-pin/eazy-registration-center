package com.open.server.net.handler;

import com.open.common.domain.InstanceInfo;
import com.open.common.util.KryoUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.net.InetSocketAddress;
import java.util.List;

import static com.open.common.enums.HealthEnum.HEALTHY;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
public class ErcConvert extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        byte[] bytes=new byte[in.writerIndex()];
        in.readBytes(bytes);
        InstanceInfo instanceInfo = (InstanceInfo) KryoUtil.doDeserialize(bytes);
        instanceInfo.setHealthCode(HEALTHY.getCode());
        instanceInfo.setAddress((InetSocketAddress) ctx.pipeline().channel().remoteAddress());
        out.add(instanceInfo);
    }


}
