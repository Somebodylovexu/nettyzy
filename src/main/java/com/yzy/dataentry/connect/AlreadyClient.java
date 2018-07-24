package com.yzy.dataentry.connect;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:连接成功的客户端信息
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
public final class AlreadyClient {

    public static final ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static final Map<String, SocketInfo> clientMap = new ConcurrentHashMap<>();

    private AlreadyClient(){

    }

    public static void add(String clientId, String channelId, SocketChannel socketChannel) {
        SocketInfo si = getClient(clientId);
        if (si == null) {
            si = new SocketInfo();
        }
        si.add(clientId, channelId, socketChannel);
        clientMap.put(clientId, si);
    }

    public static SocketInfo getClient(String clientId) {
        return clientMap.get(clientId);
    }

    public static List<SocketChannel> getSocket(String clientId) {
        List<SocketChannel> list = new ArrayList();
        SocketInfo socketInfo = getClient(clientId);
        if (socketInfo == null) {
            return null;
        }

        list.addAll(socketInfo.channel_channel.values());
        return list;
    }

    public static int size() {
        return clientMap.size();
    }

    /**
     * 根据用户ID删除SocketChannel
     * @param clientId
     */
    public static void remove(String clientId) {
        log.info("remove client:{}", clientId);
        List<SocketChannel> re = getSocket(clientId);
        if (re != null) {
            for (SocketChannel socketChannel : re) {
                group.remove(socketChannel);
            }
        }
        clientMap.remove(clientId);
    }

    /**
     * 根据SocketChannel删除
     * @param socketChannel
     */
    public static void remove(SocketChannel socketChannel) {
        if (size() == 0)
            return;

        for (Map.Entry entry : clientMap.entrySet()) {
            SocketInfo socketInfo = (SocketInfo) entry.getValue();
            socketInfo.remove(socketChannel);
        }
        group.remove(socketChannel);
    }


    private static class SocketInfo {

        private String clientId;

        private HashSet<String> channelId;

        private HashMap<String, SocketChannel> channel_channel;

        public int size() {
            return channelId == null ? 0 : channelId.size();
        }

        public HashSet<String> getChannelId() {
            return channelId;
        }

        public void add(String clientId, String channelId, SocketChannel socketChannel) {
            this.clientId = clientId;
            if (size() == 0) {
                this.channelId = new HashSet<>();
                this.channel_channel = new HashMap<>();
            }
            this.channelId.add(channelId);
            this.channel_channel.put(channelId, socketChannel);

            group.add(socketChannel);
        }

        public void remove(String channelId) {
            if (size() != 0) {
                this.channel_channel.remove(channelId);
            }
        }

        public String getChannelId(SocketChannel socketChannel) {
            if (size() == 0)
                return null;

            for (Map.Entry entry : this.channel_channel.entrySet()) {
                if (socketChannel.equals(entry.getValue())) {
                    String channelId = (String) entry.getKey();
                    return channelId;
                }
            }
            return null;
        }

        private SocketInfo remove(SocketChannel socketChannel) {
            if (size() == 0)
                return null;
            String channel = getChannelId(socketChannel);
            if (channel == null)
                return null;

            if (size() == 1) {
                clientMap.remove(this.clientId);
            } else {
                this.channelId.remove(channel);
                this.channel_channel.remove(channel);
            }

            log.info("Client:{},SocketChannel{}:has been removed.", clientId, channelId);
            return this;
        }
    }
}
