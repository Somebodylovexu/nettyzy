package com.yzy.dataentry.connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:访问控制名单
 * @Author: Jhy
 * @Date: 2018-07-28 
 */
@Component
public class AccessControl {

    @Autowired
    private AlreadyClient alreadyClient;

    //黑名单
    private Set<String> blackList = new HashSet<>();

    //白名单
    private Set<String> whiteList = new HashSet<>();

    public boolean isNotPass(String userName){
        return !isPass(userName);
    }

    public boolean isPass(String userName) {

        if (whiteList.contains(userName))
            return true;

        if (blackList.contains(userName))
            return false;

        return false;
    }

    public void saveBlack(String userName) {
        alreadyClient.remove(userName);
        blackList.add(userName);
    }

    public void removeBlack(String userName) {
        blackList.remove(userName);
    }

    public void saveWhite(String userName) {
        whiteList.add(userName);
    }

    public void removeWhite(String userName) {
        whiteList.remove(userName);
    }
}
