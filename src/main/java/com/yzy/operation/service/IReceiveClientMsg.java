package com.yzy.operation.service;
/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-05 
 */

/**
 * 处理从客户端接收的消息
 */
public interface IReceiveClientMsg {

    //响应客户端消息
    IReceiveClientMsg answer();

    //主要任务
    IReceiveClientMsg task();

    //后续流程
    IReceiveClientMsg append();

}
