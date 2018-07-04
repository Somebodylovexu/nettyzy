package com.yzy.controller;

import com.yzy.common.base.BaseMsg;
import com.yzy.common.base.LoginMsg;
import com.yzy.common.base.PingMsg;
import com.yzy.common.base.SocketMessage;
import com.yzy.common.base.type.MsgType;
import com.yzy.dataentry.connect.AlreadyChannel;
import com.yzy.operation.service.impl.AnalysisMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<SocketMessage> {

    @Autowired
    private AnalysisMsg analysisMsg;

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //channel失效，从Map中移除
        AlreadyChannel.remove((SocketChannel) ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("server exception:" + cause.getMessage());
    }

    //读取客户端消息
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SocketMessage socketMessage) throws Exception {
        analysisMsg.analysis(socketMessage);
        ReferenceCountUtil.release(socketMessage);
    }


}