package com.yzy.operation.service;
/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-05 
 */

import com.yzy.common.base.SocketMessage;

/**
 * 处理从客户端接收的消息
 */
public interface ReceiveClientMsg {

    /**
     * 相应客户端消息
     * @param socketMessage
     * @return
     */
    SocketMessage answer(SocketMessage socketMessage);
}
