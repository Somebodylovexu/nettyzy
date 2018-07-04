package com.yzy.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Component
public class StartApplication implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //启动Tcp协议的Socket
        System.out.println("启动程序！");
    }
}
