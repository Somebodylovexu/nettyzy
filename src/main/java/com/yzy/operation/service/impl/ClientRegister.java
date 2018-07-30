package com.yzy.operation.service.impl;

import com.yzy.operation.service.IReceiveClientMsg;
import io.netty.channel.socket.SocketChannel;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-05 
 */
@Service("ClientRegister")
public class ClientRegister extends AbsIReceiveClientMsg {

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public AbsIReceiveClientMsg answer() {
        super.answer("The message has been received ！");
        return this;
    }

    @Override
    public IReceiveClientMsg task() {
        String clientId = socketMessage.getClientId();
        String channelId = socketMessage.getChannelId();
        boolean result = alreadyClient.add(clientId, channelId, (SocketChannel) chx);
        if (result){
            super.answer("register success!");
        } else {
            super.answer("register fail!");
        }
        return this;
    }

    @Override
    public IReceiveClientMsg append() {
        return this;
    }
}
