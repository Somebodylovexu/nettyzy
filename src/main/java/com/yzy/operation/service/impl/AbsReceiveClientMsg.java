package com.yzy.operation.service.impl;

import com.yzy.common.base.SocketMessage;
import com.yzy.dataentry.connect.AlreadyClient;
import com.yzy.operation.service.ReceiveClientMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-05 
 */
public abstract class AbsReceiveClientMsg implements ReceiveClientMsg {

    //保存连接信息
    protected void save(SocketMessage sk,ChannelHandlerContext chc){
        AlreadyClient.add(sk.getClientId(),sk.getChannelId(), (SocketChannel) chc.channel());
    }

}
