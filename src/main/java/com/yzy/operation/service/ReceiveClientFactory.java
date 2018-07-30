package com.yzy.operation.service;

import com.yzy.common.base.SocketMessage;
import com.yzy.common.utils.SpringContextUtil;
import com.yzy.operation.service.impl.AbsIReceiveClientMsg;
import com.yzy.operation.service.impl.ClientRegister;
import com.yzy.operation.service.impl.ClientSendMsg;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-05 
 */
@Component
public class ReceiveClientFactory {

    public AbsIReceiveClientMsg createRcm(SocketMessage socketMessage) {

        if (socketMessage == null) {
            return null;
        }
        AbsIReceiveClientMsg rcm;

        int msgType = socketMessage.getData().getType();
        switch (msgType) {
            case 1:
                rcm = SpringContextUtil.getBean("ClientRegister", ClientRegister.class);
                break;
            case 101:
                rcm = SpringContextUtil.getBean("ClientSendMsg", ClientSendMsg.class);
            default:
                return null;
        }
        rcm.setSocketMessage(socketMessage);
        return rcm;
    }
}
