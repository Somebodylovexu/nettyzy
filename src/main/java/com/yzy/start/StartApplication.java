package com.yzy.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04
 */
@Component
public class StartApplication implements ApplicationRunner {

    @Autowired
    private NettyServerBootstrap nettyServerBootstrap;

    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ExecutorService service = Executors.newFixedThreadPool(2);

        try {
            //启动针对于移动设备服务
            service.execute(nettyServerBootstrap);

            //启动针对于web端服务
            service.execute(webSocketServer);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
