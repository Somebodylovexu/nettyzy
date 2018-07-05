package com.yzy.task;

import com.yzy.dataentry.connect.AlreadyChannel;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Component
@Slf4j
public class TaskTest {

    @Scheduled(fixedRate = 2000)
    public void get() {
        log.info("Current number of connections : " + AlreadyChannel.size());
    }

    @Scheduled(fixedRate = 2000)
    public void push() {
        SocketChannel channel = (SocketChannel) AlreadyChannel.get("jiang");
        if (channel != null) {
            channel.writeAndFlush("注意服务端发消息啦！");
            log.info("push message : " + "注意服务端发消息啦！");
        }
    }

}
