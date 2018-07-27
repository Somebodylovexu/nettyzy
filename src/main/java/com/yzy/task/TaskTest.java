package com.yzy.task;

import com.yzy.common.utils.ResUtil;
import com.yzy.dataentry.connect.AlreadyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
@Component
public class TaskTest {

    @Autowired
    private ResUtil resUtil;

    @Autowired
    private AlreadyClient alreadyClient;

    @Scheduled(fixedRate = 10000)
    public void get() {
        log.info("Current number of connections : " + alreadyClient.size());
    }

    @Scheduled(fixedRate = 3000)
    public void push() {
        if (alreadyClient.size() != 0)
            resUtil.send("10086", "注意服务端发消息了！");
    }

}
