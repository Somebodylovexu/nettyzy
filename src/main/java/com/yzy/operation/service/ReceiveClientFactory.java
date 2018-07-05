package com.yzy.operation.service;

import com.yzy.common.base.SocketMessage;
import com.yzy.operation.service.impl.ClientRegist;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-05 
 */
public class ReceiveClientFactory {

    public static ReceiveClientMsg createRcm(SocketMessage socketMessage){
        ReceiveClientMsg result = null;

        switch (socketMessage.getData().getType()){
            case 1:
                result = new ClientRegist();
                break;

        }
        return result;
    }
}
