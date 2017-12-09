package com.zhuangqf.learn.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhuangqf
 */
public class EchoHandler extends SimpleChannelInboundHandler<ByteBuf>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf buf) throws Exception {
        channelHandlerContext.writeAndFlush(buf.retain());
    }

}
