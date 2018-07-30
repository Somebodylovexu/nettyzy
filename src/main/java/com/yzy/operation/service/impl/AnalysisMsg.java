package com.yzy.operation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yzy.common.base.SocketMessage;
import com.yzy.dataentry.connect.AlreadyClient;
import com.yzy.operation.service.ReceiveClientFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Description:解析消息
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
@Component
public class AnalysisMsg {

    @Autowired
    private AlreadyClient alreadyClient;

    public SocketMessage analysis(String msg) throws Exception {
        log.info("Receive the message:{}" + msg);
        if (StringUtils.isEmpty(msg)) {
            return null;
        }

        //todo: 解密数据
        msg = msg;

        SocketMessage socketMessage = JSONObject.parseObject(msg, SocketMessage.class);
        return socketMessage;
    }
}
