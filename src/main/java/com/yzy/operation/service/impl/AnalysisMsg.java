package com.yzy.operation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yzy.common.base.SocketMessage;
import com.yzy.operation.service.ReceiveClientFactory;
import com.yzy.operation.service.ReceiveClientMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @Description:解析消息
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
public class AnalysisMsg {

    public static void analysis(String msg) {
        log.info("Receive the message:{}" + msg);
        if (StringUtils.isEmpty(msg)) {
            return;
        }

        SocketMessage socketMessage;
        try {
            socketMessage = JSONObject.parseObject(msg, SocketMessage.class);
        } catch (Exception e){
            return;
        }


        ReceiveClientMsg rcm = ReceiveClientFactory.createRcm(socketMessage);

    }
}
