package com.yzy.operation.service.impl;

import com.yzy.common.base.SocketMessage;
import com.yzy.common.base.type.ClientType;
import com.yzy.dataentry.connect.AlreadyClient;
import com.yzy.operation.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
@Service
public class SendServiceImpl implements SendService {


    @Override
    public void send(String clientId, SocketMessage msg) {

    }

    @Override
    public void push(ClientType clientType, SocketMessage msg) {

    }

    @Override
    public void notice(SocketMessage msg) {
        AlreadyClient.group.writeAndFlush(msg);
    }
}
