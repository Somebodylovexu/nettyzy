package com.yzy.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.yzy.common.base.MessageInfo;
import com.yzy.common.base.SocketMessage;
import com.yzy.dataentry.connect.AlreadyClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-05 
 */
@Slf4j
public class ResUtil {

    public static void send(String clientId,String msg){
        SocketMessage sm = new SocketMessage();
        sm.setClientId(clientId);
        MessageInfo mi = new MessageInfo();
        mi.setMsg(msg);
        sm.setData(mi);
        send(sm);
    }

    public static void send(SocketMessage sm) {
        List<SocketChannel> list = AlreadyClient.getSocket(sm.getClientId());
        String result = JSONObject.toJSONString(sm);
        for (SocketChannel socketChannel : list) {
            socketChannel.writeAndFlush(result);
        }
        //未知是否可行
        /*SocketChannel channel = (SocketChannel) AlreadyClient.getSocket(sm.getClientId());
        channel.writeAndFlush(result);*/
        log.info("res:{},to:{}", result, sm.getClientId());
    }

    public static void send(ChannelHandlerContext channel, SocketMessage sm) {
        String result = JSONObject.toJSONString(sm);
        channel.channel().writeAndFlush(result);
        log.info("res:{},to:{}", result, sm.getClientId());
    }
}
