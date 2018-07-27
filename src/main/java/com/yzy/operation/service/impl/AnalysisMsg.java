package com.yzy.operation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yzy.common.base.SocketMessage;
import com.yzy.operation.service.ReceiveClientFactory;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
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

    public SocketMessage analysis(String msg, ChannelHandlerContext ctx) {
        log.info("Receive the message:{}" + msg);
        if (StringUtils.isEmpty(msg)) {
            return null;
        }


        //todo: 解密数据
        msg = msg;

        //
        SocketMessage socketMessage = JSONObject.parseObject(msg, SocketMessage.class);


/*        AbsReceiveClientMsg rcm = ReceiveClientFactory.createRcm(socketMessage);
        rcm.save(socketMessage, ctx);
        rcm.answer(socketMessage, ctx);*/

        return socketMessage;
    }
}
