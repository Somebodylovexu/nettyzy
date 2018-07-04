package com.yzy.common.base;

import com.yzy.common.base.type.MsgType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Getter
@Setter
public abstract class BaseMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private MsgType type;
    //必须唯一，否者会出现channel调用混乱
    private String clientId;

    //初始化客户端id
    public BaseMsg() {

        this.clientId = Constants.getClientId();
    }

}

