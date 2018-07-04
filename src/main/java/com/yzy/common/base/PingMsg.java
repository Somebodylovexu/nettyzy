package com.yzy.common.base;

import com.yzy.common.base.type.MsgType;

/**
 * @Description:心跳检测消息类型
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
public class PingMsg extends BaseMsg {
    public PingMsg() {
        super();
        super.setType(MsgType.PING);
    }
}
