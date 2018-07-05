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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        new NettyServerBootstrap(9999);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    new WebSocketServer(9998);
                }
            }).start();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
