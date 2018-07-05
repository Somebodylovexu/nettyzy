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
public class SocketMessage implements Serializable {

    private static final long serialVersionUID = 8707854634863863040L;

    private String channelId;//根据客户端生成的ID

    private String clientId;//用户ID

    private String clientTag;//客户端标识

    private String tokenId;

    private MessageInfo data;

}
