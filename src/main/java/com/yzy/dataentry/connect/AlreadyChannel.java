package com.yzy.dataentry.connect;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:连接成功的客户端SocketChannel信息
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
public class AlreadyChannel {

    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static Map<String, SocketChannel> map = new ConcurrentHashMap<>();

    public static void add(String channelId, SocketChannel socketChannel) {
        map.put(channelId, socketChannel);
        group.add(socketChannel);
        log.info("AlreadyChannel add : " + channelId);
    }

    public static Channel get(String channelId) {
        return map.get(channelId);
    }

    public static int size() {
        return map.size();
    }

    public static void remove(SocketChannel socketChannel) {

        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == socketChannel) {
                String key = (String) entry.getKey();
                map.remove(key);
                group.remove(socketChannel);
                log.info("Clinet:" + key + " has been removed.");
            }
        }
    }
}
