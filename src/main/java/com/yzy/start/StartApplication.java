package com.yzy.start;

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

        try {
            //启动针对于移动设备服务
            new Thread(() -> new NettyServerBootstrap(9999)).start();

            //启动针对于web端服务
            new Thread(() -> new WebSocketServer(9998)).start();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
