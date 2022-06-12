package com.open.client.net.handler;

import com.open.common.domain.ResponseMessage;
import com.open.common.util.KryoUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author rich
 * @date 2022/6/11
 * @description
 */
public class ByteToResponseMessage extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        byte[] bytes = new byte[in.writerIndex()];
        in.readBytes(bytes);
        ResponseMessage responseMessage = (ResponseMessage) KryoUtil.doDeserialize(bytes);
        out.add(responseMessage);
    }


}
