package com.yzy.operation.service.impl;

import com.yzy.common.base.SocketMessage;
import com.yzy.dataentry.connect.AlreadyClient;
import com.yzy.operation.service.ReceiveClientMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-05 
 */
public abstract class AbsReceiveClientMsg implements ReceiveClientMsg {

    @Autowired
    private AlreadyClient alreadyClient;

    //保存连接信息
    protected void save(SocketMessage sk,ChannelHandlerContext chc){
        alreadyClient.add(sk.getClientId(),sk.getChannelId(), (SocketChannel) chc.channel());
    }

}
