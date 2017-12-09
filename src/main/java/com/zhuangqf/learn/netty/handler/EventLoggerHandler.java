package com.zhuangqf.learn.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuangqf
 */
public class EventLoggerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(EventLoggerHandler.class);
    private static final String MESSAGE = "[Channel Event Log:{}] {}";

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.debug(MESSAGE,ctx.name(),"channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.debug(MESSAGE,ctx.channel().remoteAddress(),"channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug(MESSAGE,ctx.channel().remoteAddress(),"channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.debug(MESSAGE,ctx.channel().remoteAddress(),"channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        logger.debug(MESSAGE,ctx.channel().remoteAddress(),"channelRead:"+ in.toString(CharsetUtil.UTF_8));
        super.channelRead(ctx,msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.debug(MESSAGE,ctx.channel().remoteAddress(),"channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        logger.debug(MESSAGE,ctx.channel().remoteAddress(),"userEventTriggered:"+evt);
        super.userEventTriggered(ctx,evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        logger.debug(MESSAGE,ctx.channel().remoteAddress(),"channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(MESSAGE,ctx.channel().remoteAddress(),"exceptionCaught："+cause.getMessage());
        cause.printStackTrace();
        super.exceptionCaught(ctx,cause);
    }
}

