package com.yzy.common.base;

/**
 * @Description:消息基本信息
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
public class Constants {

    private static String clientId;

    public static String getClientId() {
        return clientId;
    }

    public static void setClientId(String clientId) {
        Constants.clientId = clientId;
    }
}
