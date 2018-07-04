package com.yzy.dataentry.connect;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:保存连接成功的客户端
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
public class AlreadyChannel {

    private static Map<String, SocketChannel> map = new ConcurrentHashMap<>();

    public static void add(String channelId, SocketChannel socketChannel) {
        map.put(channelId, socketChannel);
    }

    public static Channel get(String channelId) {
        return map.get(channelId);
    }

    public static void remove(SocketChannel socketChannel) {

        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == socketChannel) {
                String key = (String) entry.getKey();
                System.out.println("Clinet:" + key + " has been removed.");
                map.remove(key);
            }
        }
    }
}
