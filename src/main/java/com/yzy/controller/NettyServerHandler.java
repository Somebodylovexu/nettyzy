package com.yzy.controller;

import com.yzy.common.base.BaseMsg;
import com.yzy.common.base.LoginMsg;
import com.yzy.common.base.PingMsg;
import com.yzy.common.base.type.MsgType;
import com.yzy.dataentry.connect.AlreadyChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMsg> {

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
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
        if (MsgType.LOGIN.equals(baseMsg.getType())) {
            LoginMsg loginMsg = (LoginMsg) baseMsg;
            if (isLogin(loginMsg)) {
                //登录成功,把channel存到服务端的map中
                AlreadyChannel.add(loginMsg.getClientId(), (SocketChannel) channelHandlerContext.channel());
                log.info("client:" + loginMsg.getClientId() + " landing success !");
            }
        } else {
            if (AlreadyChannel.get(baseMsg.getClientId()) == null) {
                //说明未登录，或者连接断了，服务器向客户端发起登录请求，让客户端重新登录
                LoginMsg loginMsg = new LoginMsg();
                channelHandlerContext.channel().writeAndFlush(loginMsg);
            }
        }
        switch (baseMsg.getType()) {
            case PING:
                PingMsg pingMsg = (PingMsg) baseMsg;
                PingMsg replyPing = new PingMsg();
                AlreadyChannel.get(pingMsg.getClientId()).writeAndFlush(replyPing);
                log.info("收到PING类型");
                break;
            case LOGIN:
                break;
            case PUSH:
                break;
            default:
                log.info("default。。");
                break;
        }
        ReferenceCountUtil.release(baseMsg);
    }

    private boolean isLogin(LoginMsg loginMsg) {
        String[] userNames = new String[]{"test1", "test2", "test3"};
        String[] passwords = new String[]{"test1", "test2", "test3"};
        for (int i = 0; i < userNames.length; i++) {
            try {
                if (userNames[i].equals(loginMsg.getUserName()) && passwords[i].equals(loginMsg.getPassword())) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }

}