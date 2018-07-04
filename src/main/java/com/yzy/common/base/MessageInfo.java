package com.yzy.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-03 
 */
@Getter
@Setter
public class MessageInfo implements Serializable {

    private static final long serialVersionUID = 3389863976079589069L;

    private int type;//消息类型

    private int stateCode;//状态码

    private String msg;

}
