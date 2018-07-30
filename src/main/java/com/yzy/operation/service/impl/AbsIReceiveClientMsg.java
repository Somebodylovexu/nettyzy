package com.yzy.operation.service.impl;

import com.yzy.common.base.SocketMessage;
import com.yzy.common.utils.ResUtil;
import com.yzy.dataentry.connect.AlreadyClient;
import com.yzy.operation.service.IReceiveClientMsg;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-05 
 */
public abstract class AbsIReceiveClientMsg implements IReceiveClientMsg {

    @Autowired
    protected AlreadyClient alreadyClient;

    @Autowired
    protected ResUtil resUtil;

    protected SocketMessage socketMessage;

    protected ChannelHandlerContext chx;

    public abstract boolean check();

    public SocketMessage getSocketMessage() {
        return socketMessage;
    }

    public void setSocketMessage(SocketMessage socketMessage) {
        this.socketMessage = socketMessage;
    }

    protected void answer(String msg) {
        resUtil.send(socketMessage.getClientId(), msg);
    }

    public ChannelHandlerContext getChx() {
        return chx;
    }

    public AbsIReceiveClientMsg setChx(ChannelHandlerContext chx) {
        this.chx = chx;
        return this;
    }
}
