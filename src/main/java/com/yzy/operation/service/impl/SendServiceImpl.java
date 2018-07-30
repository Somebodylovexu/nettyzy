package com.yzy.operation.service.impl;

import com.yzy.common.base.SocketMessage;
import com.yzy.common.base.type.ClientType;
import com.yzy.common.utils.ResUtil;
import com.yzy.dataentry.connect.AlreadyClient;
import com.yzy.operation.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
@Service("SendServiceImpl")
public class SendServiceImpl implements SendService {

    @Autowired
    private ResUtil resUtil;

    @Override
    public void send(String clientId, SocketMessage socketMessage) {
        String msg = socketMessage.getData().getMsg();
        resUtil.send(clientId, msg);
    }

    @Override
    public void push(ClientType clientType, SocketMessage msg) {

    }

    @Override
    public void notice(SocketMessage socketMessage) {
        String msg = socketMessage.getData().getMsg();
        AlreadyClient.group.writeAndFlush(msg);
    }
}
