package com.yzy.task;

import com.yzy.common.base.SocketMessage;
import com.yzy.common.utils.ResUtil;
import com.yzy.dataentry.connect.AlreadyChannel;
import com.yzy.dataentry.connect.AlreadyClient;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Component
@Slf4j
public class TaskTest {

    @Scheduled(fixedRate = 10000)
    public void get() {
        log.info("Current number of connections : " + AlreadyClient.size());
    }

    @Scheduled(fixedRate = 3000)
    public void push() {
        if (AlreadyClient.size() != 0)
            ResUtil.send("10086", "注意服务端发消息了！");
    }

}
