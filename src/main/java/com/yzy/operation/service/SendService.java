package com.yzy.operation.service;

import com.yzy.common.base.SocketMessage;
import com.yzy.common.base.type.ClientType;

/**
 * @Description:发送消息业务
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
public interface SendService {

    //定向推送消息
    void send(String clientId, SocketMessage msg);

    //发送广播
    void push(ClientType clientType, SocketMessage msg);

    //发送到所有在线设备
    void notice(SocketMessage msg);
}
