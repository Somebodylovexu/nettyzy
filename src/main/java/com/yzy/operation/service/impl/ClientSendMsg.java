package com.yzy.operation.service.impl;

import com.yzy.common.base.MessageInfo;
import com.yzy.operation.service.IReceiveClientMsg;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-30 
 */
@Service("ClientSendMsg")
public class ClientSendMsg extends AbsIReceiveClientMsg {

    @Override
    public IReceiveClientMsg answer() {
        super.answer("The message has been received ！");
        return this;
    }

    @Override
    public IReceiveClientMsg task() {
        MessageInfo info = socketMessage.getData();
        String receive = info.getReceive();
        resUtil.send(receive, info.getMsg());
        return this;
    }

    @Override
    public IReceiveClientMsg append() {
        return this;
    }

    @Override
    public boolean check() {
        return true;
    }
}
