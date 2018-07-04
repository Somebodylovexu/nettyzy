package com.yzy.common.base;

import com.yzy.common.base.type.MsgType;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Getter
@Setter
public class LoginMsg extends BaseMsg {

    private String userName;

    private String password;

    public LoginMsg() {
        super();
        super.setType(MsgType.LOGIN);
    }
}

