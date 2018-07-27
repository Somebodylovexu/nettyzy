package com.yzy.operation.service.impl;

import com.yzy.common.base.SocketMessage;
import com.yzy.common.utils.ResUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-05 
 */
public class ClientRegist extends AbsReceiveClientMsg {

    @Autowired
    private ResUtil resUtil;

    @Override
    public void answer(SocketMessage socketMessage, ChannelHandlerContext channel) {
        SocketMessage res = new SocketMessage();
        BeanUtils.copyProperties(socketMessage, res);
        res.getData().setMsg("你的请求已经收到啦！");
        resUtil.send(channel, res);
    }
}
