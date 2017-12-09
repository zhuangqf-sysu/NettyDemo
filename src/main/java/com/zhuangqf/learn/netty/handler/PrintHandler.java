package com.zhuangqf.learn.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuangqf
 */
public class PrintHandler extends SimpleChannelInboundHandler<ByteBuf>{

    private static final Logger logger = LoggerFactory.getLogger(PrintHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {
        logger.info("receive:"+ msg.toString(CharsetUtil.UTF_8));
    }
}
