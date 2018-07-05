package com.yzy.common.base.type;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：hpp
 * @date：2018/7/5 12:40
 * @description: 不同的echarts类型
 */
public class EchartsSession {
    private static final Map<String, ChannelGroup> SESSION = new HashMap<>();

    static {
        ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        SESSION.put(EchartsType.PRODUCT_TYPE, group);
    }

    public static void remove(Channel o) {
        for (Map.Entry kv : SESSION.entrySet()) {
            ChannelGroup c = (ChannelGroup) kv.getValue();
            boolean isBreak = false;
            if (c.remove(o)) {
                isBreak = true;
            }
            if (isBreak) {
                break;
            }
        }
    }

    public static ChannelGroup get(String type) {
        for (Map.Entry e : SESSION.entrySet()) {
            if (e.getKey().equals(type)) {
                return SESSION.get(e.getKey());
            }
        }
        return null;
    }


    public static void put(String type, Channel channel) {
        boolean isPut = false;
        for (Map.Entry e : SESSION.entrySet()) {
            if (e.getKey().equals(type)) {
                SESSION.get(e.getKey()).add(channel);
                isPut = true;
                break;
            }
        }
        if (isPut == false) {
            throw new RuntimeException("echarts type session {" + type + "} is not defined");
        }
    }
}
